import java.util.HashMap;

public class GCD implements Simulator {
  public int x = 0;
  public int y = 0;
  public boolean clk = false;
  public boolean reset = false;
  public int[] io_a = new int[]{0};
  public int[] io_b = new int[]{0};
  public boolean io_e = true;
  public int[] io_z = new int[]{0};
  public boolean io_v = false;
  public void eval(boolean update_registers, boolean verbose, boolean done_reset) {
    boolean T_7 = x > y;
    int T_8 = x - y;
    int T_9 = T_8 & 0xffff;
    int _GEN_0 = T_7 ? T_9 : x;
    boolean T_12 = !T_7;
    int T_13 = y - x;
    int T_14 = T_13 & 0xffff;
    int _GEN_1 = T_12 ? T_14 : y;
    io_z[0] = x;
    io_v = y == 0;
    int x$next = io_e ? io_a[0] : _GEN_0;
    int y$next = io_e ? io_b[0] : _GEN_1;
    if (update_registers) x = x$next;
    if (update_registers) y = y$next;
  }

  public HashMap<String, int[]> regs;

  public GCD() {
    regs = new HashMap<>();
    regs.put("io_a", io_a);
    regs.put("io_b", io_b);
    regs.put("io_z", io_z);
  };

  public int peek(String var) {
    return regs.get(var)[0];
  }

  public void poke(String var, int val) {
    regs.get(var)[0] = val;
  }

  public void step() {
    eval(true, true, true);
  }
}
