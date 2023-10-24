package _Typer;

public class OperationMap {
    private static int iForward;
    private static String comStr;
    private static boolean isSpecial;
    private static boolean isUpperCase;
    private static boolean isAddBefore;
    private static boolean isAddAfter;
    private static boolean isUpTomorrow;
    private static boolean isUpFull;

    public OperationMap() {
    }

    public OperationMap(int iForward, String comStr, boolean isSpecial, boolean isUpperCase, boolean isAddBefore, boolean isAddAfter, boolean isUpTomorrow, boolean isUpFull) {
            this.iForward = iForward;
            this.comStr = comStr;
            this.isSpecial = isSpecial;
            this.isUpperCase = isUpperCase;
            this.isAddBefore = isAddBefore;
            this.isAddAfter = isAddAfter;
            this.isUpTomorrow = isUpTomorrow;
            this.isUpFull = isUpFull;
    }

    public void setiForward(int iForward) {
        this.iForward = iForward;
    }
    public void setComStr(String comStr) {
        this.comStr = comStr;
    }
    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }
    public void setIsUpperCase(boolean isUpperCase) {
        this.isUpperCase = isUpperCase;
    }
    public void setIsAddBefore(boolean isAddBefore) {
        this.isAddBefore = isAddBefore;
    }public void setIsAddAfter(boolean isAddAfter) {
        this.isAddAfter = isAddAfter;
    }
    public void setIsUpTomorrow(boolean isUpTomorrow) {
        this.isUpTomorrow = isUpTomorrow;
    }
    public void setIsUpFull(boolean isUpFull) {this.isUpFull = isUpFull; }
    public int getiForward() {
        return iForward;
    }
    public String getComStr() {
        return comStr;
    }
    public boolean GetIsSpecial() {
        return isSpecial;
    }
    public boolean GetIsUpperCase() {
        return isUpperCase;
    }
    public boolean GetIsAddBefore() {
        return isAddBefore;
    }
    public boolean GetIsAddAfter() {
        return isAddAfter;
    }
    public boolean GetIsUpTomorrow() {
        return isUpTomorrow;
    }
    public boolean GetIsUpFull() {
        return isUpFull;
    }

}

