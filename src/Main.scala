import java.net.URLClassLoader
import javax.tools.ToolProvider
import java.nio.file.Path

object Main {
  def main(args: Array[String]): Unit = {
    val filename = "/home/kevin/d/java-runtime-compile/test.java"
    val compiler = ToolProvider.getSystemJavaCompiler
    // JavaCompiler docs: https://docs.oracle.com/javase/8/docs/api/javax/tools/JavaCompiler.html
    //            stdin?           stdout?            stderr?            arguments as string
    // compiler.run(null, null, null, filename)
    //     out - "standard" output; use System.out if null
    compiler.run(null, null, null, "--help")

    // how to provide javac with access to our class path?
  }

  // code from https://blog.frankel.ch/compilation-java-code-on-the-fly/

  // https://www.netjstech.com/2016/10/how-to-compile-java-program-at-runtime.html

//  private def compileSource(javaFile: Path) = {
//    val compiler = ToolProvider.getSystemJavaCompiler
//    compiler.run(null, null, null, javaFile.toFile.getAbsolutePath)
//    javaFile.getParent.resolve("Harmless.class")
//  }
//
//
//  private def runClass(javaClass: Path): Unit = {
//    val classUrl = javaClass.getParent.toFile.toURI.toURL
//    val classLoader = URLClassLoader.newInstance(Array[Nothing](classUrl))
//    val clazz = Class.forName("Harmless", true, classLoader)
//    clazz.newInstance
//  }
}
