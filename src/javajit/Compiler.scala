package javajit

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.net.URLClassLoader
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}
import java.util.stream.Collectors
import javax.tools.ToolProvider

object Compiler {
  def compile(sourcePath: String): Simulator = {
    val source = readCode(sourcePath)
    val name = Paths.get(sourcePath).getFileName.toString.split('.')(0)
    val javaFile = saveSource(source, name)
    val classFile: Path = compileSource(javaFile, name)
    loadClass(classFile, name)
  }

  def compileCode(code: String, name: String): Simulator = {
    val javaFile = saveSource(code, name)
    val classFile: Path = compileSource(javaFile, name)
    loadClass(classFile, name)
  }

  private def loadClass(javaClass: Path, name: String): Simulator = {
    val classUrl = javaClass.getParent.toFile.toURI.toURL
    val currentClassLoader = Thread.currentThread.getContextClassLoader
    val classLoader = URLClassLoader.newInstance(Array[java.net.URL](classUrl), currentClassLoader)
    val cls = classLoader.loadClass(name)
    val instance = cls.getDeclaredConstructor().newInstance()
    instance.asInstanceOf[Simulator]
  }

  import CompilerHelpers._

  private def compileSource(javaFile: Path, name: String): Path = {
    val compiler = ToolProvider.getSystemJavaCompiler
    // explicitly specify the class path
    compiler.run(null, null, null, "-classpath", getClassPath.mkString(":"), javaFile.toFile.getAbsolutePath)
    javaFile.getParent.resolve(name + ".class")
  }

  private def saveSource(source: String, name: String): Path = {
    val tmpProperty = System.getProperty("java.io.tmpdir")
    val sourcePath = Paths.get(tmpProperty, name + ".java")
    Files.write(sourcePath, source.getBytes(StandardCharsets.UTF_8))
  }

  private def readCode(sourcePath: String): String = {
    val stream = new FileInputStream(sourcePath)
    val separator = System.getProperty("line.separator")
    val reader = new BufferedReader(new InputStreamReader(stream))
    reader.lines.collect(Collectors.joining(separator))
  }
}

private object CompilerHelpers {
  /** Returns the current class path, taking into account sbt's layered class loader strategy */
  def getClassPath: Seq[String] = {
    val loader = Thread.currentThread().getContextClassLoader
    loader match {
      // this matches sbt's layered class loader from which we can extract the local class path
      case url : URLClassLoader => url.getURLs.map(_.getPath)
      // in a forked JVM, the default Java class loader will be active and we can just use the system path
      case _ => System.getProperty("java.class.path").split(':')
    }
  }
}