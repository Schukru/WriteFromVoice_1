package _Typer;

import Utils.MyUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class SpeechTexter {

    static int iForward = 0;
    static String commandStr = "";
    private static _SpeechTextPage sp = new _SpeechTextPage();
    private static String longText;
    private static Robot robot;
    private static String keyboardStr = "";
    private static String commandKeyboard = "";


    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void chooseCountry(){
        MyUtils.clickFunction(sp.getElement("chooseCountryButton"));
        MyUtils.clickFunction(sp.getElement("chooseTurkeyLine"));
    }

    public static void unCheckedBox(){
        MyUtils.clickFunction(sp.getElement("settingsButton"));
        MyUtils.clickWithActions(sp.getElement("firstLetterCheckBox"));
        MyUtils.clickFunction(sp.getElement("settingsCloseButton"));
    }

    public static String getLongText() {
        longText = MyUtils.getElementText(sp.getElement("writtenText"));
        return longText;
    }

    public static void clearPage(String longText){
        if (!longText.isEmpty()) {
            MyUtils.clickFunction(sp.getElement("clearTextButton"));
        }
    }

    public static void startRecord(){
        MyUtils.clickFunction(sp.getElement("startStopButton"));
    }

    public static void stopRecord(){
        MyUtils.clickFunction(sp.getElement("startStopButton"));
    }

    public static OperationMap operationKey(String[] kelimeler, OperationMap opM, int i){

        iForward = 0;
        commandStr = kelimeler[i];

          switch (kelimeler[i]){                                    // tek kelimelik komutlar
            case "enter" :
                commandStr= "enter"; iForward = 1; break;
            case "backspace":
                commandStr= "backspace"; iForward = 1; break;
            case "boşluk":
            case "space":
                commandStr= "space"; iForward = 1; break;
            case "sekme":
            case "tab":
            case "tap":
                commandStr= "tab"; iForward = 1; break;
            case "del":
            case "delete":
                commandStr= "delete"; iForward = 1; break;
            case "eşit":
            case "eşittir":
                commandStr= "equals"; iForward = 1; break;
        }

        if (kelimeler.length > i+1) {

            switch (kelimeler[i].concat(" ").concat(kelimeler[i+1])){         // iki kelimelik komutlar
                case "satır sonu" :
                    commandStr= "enter"; iForward = 2; break;
                case "boşluk bırak":
                    commandStr= "space"; iForward = 2; break;
                case "sağa git":
                    commandStr= "right"; iForward = 2; break;
                case "aşağı in":
                case "aşağıya in":
                case "aşağıya gel":
                    commandStr= "PgDn"; iForward = 2; break;
                case "yukarı çık":
                case "yukarı gel":
                    commandStr= "PgUp"; iForward = 2; break;
                case "sola git":
                case "sola gel":
                case "geri gel":
                    commandStr= "left"; iForward = 2; break;
                case "back space":
                    commandStr= "backspace"; iForward = 2; break;
                case "ileri al":
                    commandStr= "ctrl+y"; iForward = 2; break;
                case "geri al":
                    commandStr= "ctrl+z"; iForward = 2; break;
                case "başa gel":
                    commandStr= "home"; iForward = 2; break;
                case "sona git":
                    commandStr= "end"; iForward = 2; break;
                case "programı durdur":
                    commandStr = ""; iForward = 2; TypeFromSpeech.isPause = true; soundAlert("pause");break;
                case "programı başlat":
                    commandStr= ""; iForward = 2; TypeFromSpeech.isPause = false; soundAlert("start");break;

            }
        }

        if (kelimeler.length > i+2) {

            switch (kelimeler[i].concat(" ").concat(kelimeler[i+1]).concat(" ").concat(kelimeler[i+2])) {             // üç kelimelik komutlar
                case "bir geri gel":
                case "bi geri gel":
                    commandStr = "left" ; iForward = 3; break;
                case "bir yukarı çık":
                case "bir yukarıya çık":
                case "bir üste çık":
                    commandStr = "up"; iForward = 3; break;
                case "bir aşağıya in":
                case "bir aşağı in":
                    commandStr= "down"; iForward = 3; break;
                case "en üste gel":
                case "en başa git":
                    commandStr = "ctrl+home"; iForward = 3; break;
                case "en sona git":
                    commandStr = "ctrl+end"; iForward = 3; break;
                case "satır başı yap":
                    commandStr = "enter"; iForward = 3; break;
                case "satır başına git":
                    commandStr = "home"; iForward = 3; break;
                case "satır sonuna git":
                case "en sonuna git":
                    commandStr = "end"; iForward = 3; break;
                case "yazmaya devam et":
                    commandStr= ""; iForward = 3; TypeFromSpeech.isPause = false; soundAlert("start");break;

            }
        }

        if (kelimeler.length > i+3) {

            switch (kelimeler[i].concat(" ").concat(kelimeler[i+1]).concat(" ").concat(kelimeler[i+2])
                                                .concat(" ").concat(kelimeler[i+3])) {                    // dört kelimelik komutlar
                case "for next döngüsü oluştur":
                    commandStr = "{Enter}for (i=0; i<  ; i++){}" ; iForward = 4; break;

            }
        }

        keyboardStr = putIntoKeyboard(commandStr);

        if (commandStr.equals(keyboardStr)){   // eğer bir komut değeri değilse
            keyboardStr = keyboardStr.concat(" ");
        }

        opM.setiForward(iForward);
        opM.setComStr(keyboardStr);

        return opM;
    }

    public static String putIntoKeyboard(String commandStr){

        commandKeyboard = commandStr;

        switch (commandStr){                                    // klavye komutunu öğren
            case "enter" :
                commandKeyboard = "{Enter}"; break;
            case "backspace":
                commandKeyboard = "{Backspace}"; break;
            case "space":
                commandKeyboard = "{Space}"; break;
            case "tab":
                commandKeyboard = "{Tab}"; break;
            case "delete":
                commandKeyboard = "{Del}"; break;
            case "insert":
                commandKeyboard = "{Ins}"; break;
            case "home":
                commandKeyboard = "{Home}"; break;
            case "end":
                commandKeyboard = "{End}"; break;
            case "PgUp":
                commandKeyboard = "{PgUp}"; break;
            case "PgDn":
                commandKeyboard = "{PgDn}"; break;
            case "left":
                commandKeyboard = "{Left}"; break;
            case "right":
                commandKeyboard = "{Right}"; break;
            case "down":
                commandKeyboard = "{Down}"; break;
            case "up":
                commandKeyboard = "{Up}"; break;
            case "ctrl+home":
                commandKeyboard = "^{Home}"; break;
            case "ctrl+end":
                commandKeyboard = "^{End}"; break;
            case "ctrl+z":
                commandKeyboard = "^{z}"; break;
            case "ctrl+y":
                commandKeyboard = "^{y}"; break;
            case "equals":
                commandKeyboard = " = "; break;
        }

        return commandKeyboard;
    }

    public static void importTheCommandsFile()  {

        MyUtils.clickFunction(sp.getElement("editButton"));

        MyUtils.clickFunction(sp.getElement("importButton"));
        MyUtils.waitFor(1000);

        String filePath = System.getProperty("user.dir") +
                "\\src\\test\\resources\\SpeechTexter_Turkish_commands.txt";

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection stringSelection = new StringSelection(filePath);
        clipboard.setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        MyUtils.waitFor(500);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_TAB);   //Tab tuşuna basıldı
        robot.keyRelease(KeyEvent.VK_TAB);  // Tab serbest bırakıldı

        robot.keyPress(KeyEvent.VK_TAB);     // aynı işlem ikinci defa yapılıyor
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    public static void soundAlert(String whichVoice) {

        String soundFilePath = System.getProperty("user.dir") + "/src/test/resources/voices/";
        String fName = "";

        switch (whichVoice) {
            case "start" : fName = "StartApp.wav"; break;
            case "pause" : fName = "PauseApp.wav"; break;
            case "stop" : fName = "StopApp.wav"; break;
            case "gpt" : fName = "ChatGPT.wav"; break;
            default: fName = "typewriter_click.wav";
        }
        soundFilePath += fName;

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
            }
    }

}

