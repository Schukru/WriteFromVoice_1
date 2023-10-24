package _Typer;

import Utils.Driver;
import Utils.MyUtils;

import java.io.IOException;

public class _TypeFromSpeech {

    public static boolean isPause = false;
    private static SpeechTextPage sp = new SpeechTextPage();
    private static int iForward = 0;
    private static String commandStr = "";
    private static boolean isSpecial = false;
    private static boolean isUpperCase = true;
    private static boolean isAddBefore = false;
    private static boolean isAddAfter = true;
    private static boolean isUpTomorrow = false;
    private static boolean isUpFull = false;
    private static int n;
    private static OperationMap opM = new OperationMap(iForward, commandStr, isSpecial, isUpperCase, isAddBefore, isAddAfter, isUpTomorrow, isUpFull);


    public static void main(String[] args) {

        String longText;

        Driver.getDriver().get("https://www.speechtexter.com/");
        SpeechTexter.chooseCountry();
        SpeechTexter.unCheckedBox();
        SpeechTexter.importTheCommandsFile();

        SpeechTexter.startRecord();
        SpeechTexter.soundAlert("start");
        MyUtils.waitFor(2000);

        while (true) {

            longText = SpeechTexter.getLongText();
            if (!longText.isEmpty()) {
                wordsToType(longText);
                SpeechTexter.clearPage(longText);
            }
        }
    }

    private static void wordsToType(String longText) {

        String[] kelimeler = longText.toLowerCase().split(" ");
        n = kelimeler.length;
        iForward = 0;

        for (int i = 0; i < n; i++) {

            opM = SpeechTexter.operationKey(kelimeler, opM, i);

            iForward = opM.getiForward();
            commandStr = opM.getComStr();

            putIntoWords(commandStr);
            i += iForward;

            opM.setIsSpecial(false);
            opM.setIsUpperCase(false);
        }

    }

    private static void putIntoWords(String text) {

        String autoHotKeyPath = "C:\\Program Files\\AutoHotkey\\v2\\autohotkey.exe";
        String ahkFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\send_text.ahk";

        if (!text.isEmpty() & !_TypeFromSpeech.isPause) {

            try {
                ProcessBuilder pb = new ProcessBuilder(autoHotKeyPath, ahkFilePath, text);
                pb.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
