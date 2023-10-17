package _Typer;

public class OperationMap {
    private static int iForward;
    private static String comStr;

    public OperationMap() {
    }

    public OperationMap(int iForward, String comStr) {
            this.iForward = iForward;
            this.comStr = comStr;
    }

    public void setiForward(int iForward) {
        this.iForward = iForward;
    }

    public void setComStr(String comStr) {
        this.comStr = comStr;
    }

    public int getiForward() {
        return iForward;
    }

    public String getComStr() {
        return comStr;
    }

}

