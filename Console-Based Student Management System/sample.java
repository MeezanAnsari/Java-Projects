public class sample {
    public static void main(String[] args) {
        int s_id = 1;
        String s_name = "Meezan";
        String s_dept = "Meezan";
        String s_class = "Meezan";
        int jpr = 20;
        int dcc = 20;
        int mic = 20;
        int sen = 20;
        String insertString = "insert into students values("+s_id+","+"'"+s_name+"'"+","+"'"+s_dept+"'"+","+"'"+s_class+"'"+","+jpr+","+dcc+","+mic+","+sen+")";
        System.out.println(insertString);
    }
}
