package gui;

import java.awt.Color;
import java.awt.Dimension;

/**
 * 
 */

/**
 * @author ThanhNhut
 *
 */
public class constants {
	public static final String APPNAME = "Character Code Generator";
	public static final String VERSION = "1.0";
	
    public static final String CHARACTERPANELTITLE = "Character pattern";
    public static final String SETTINGPANELTITLE = "Settings";
    public static final String CREATEPANELTITLE = "Create character layout";
    public static final String FILEPANELTITLE = "Code table file";
    public static final String CODEPANELTITLE = "Code format";
    public static final Dimension PANELSIZE = new Dimension(800, 1000);
    public static final Dimension CHARACTERPANELSIZE = new Dimension(300, 1000);
    public static final Dimension SETTINGPANELSIZE = new Dimension(500, 1000);
    public static final Dimension CREATEPANELSIZE = new Dimension(480, 200);
    public static final Dimension FILEPANELSIZE = new Dimension(480, 400);
    public static final Dimension CODEPANELSIZE = new Dimension(480, 400);
    
    public static final String SIZELABEL = "Size";
    public static final Dimension TFSIZE = new Dimension(20, 20);
    public static final String SHOWLAYOUTBTLABEL = "Create/Clear";
    public static final String REVERSEBTLABEL = "Reverse";
    
    public static final String PATHLABEL = "Path";
    public static final String CODETABLELENGTHLABEL = "Number of characters :";
    public static final String BROWSEBTLABEL = "...";
    public static final String SAVEBTLABEL = "Save";
    public static final String ADDBTLABEL = "Add";
    public static final String CLEARBTLABEL = "Clear";
   
    public static final String CHARACTERCODENAMELABEL = "Char name";
    public static final String CODEFORMATLABEL = "Format";
    public static final String APPLYBTLABEL = "Apply";
    public static final String MODEBTLABEL = "Set mode";
    public static final String CHANGEBTLABEL = "Change";
    
    public static final Color BIT1COLOR = Color.RED;
    public static final Color BIT0COLOR = Color.WHITE;
    public static final Dimension DOTSIZE = new Dimension(20, 20);
    
    public static final String CODEDEFINEFORMAT = "char c_%s[%d]%s = {%d, %s};%s";
    public static final String FILEFILTERDESC = "Header file .h ";
    public static final String EXTENSIONFILTER = "h";
    
    public static final String OPENBTLABEL = "Open";
    
    public static final String CONFIRMBTLABEL = "Confirm";
    public static final String CANCELBTLABEL = "Cancel";
    public static final String SELECTMODEDIALOGTITLE = "Select create mode";
    public static final String SELECTMODEDIALOGMESSAGE = "Select mode :";
    public static final int FREEMODE = 0;
    public static final String FREEMODESTRING = "Free mode";
    public static final int HALFMODE = 1; // Char 33 - 127
    public static final String HALFMODESTRING = "Half mode";
    public static final int FULLMODE = 2; // Char 33 - 255
    public static final String FULLMODESTRING = "Full mode";
    public static final int CTRLHALFMODE = 3; // Char 0 - 127
    public static final String CTRLHALFMODESTRING = "Control half mode";
    public static final int CTRLFULLMODE = 4; // Char 0 - 255
    public static final String CTRLFULLMODESTRING = "Control full mode";    
}
