/*
 * Created on Jan 30, 2006
 */
package eionet.helpadm.util;

import java.util.HashSet;

/**
 * @author jaanus
 */
public class Util {

    /** */
    private static HashSet xhtmlEntities = null;

    /*
     *
     */
    public static String escapeEscapedHTML(String s){

        if (s==null || s.length()==0) {
            return s;
        }

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '&'){
                int index = s.indexOf(';', i);
                if (index != -1){
                    String subs = s.substring(i, index+1);
                    if (Util.isNumericHTMLEscapeCode(subs) || Util.isXHTMLEntity(subs)){
                        buf.append("&amp;").append(subs.substring(1));
                        i = index;
                        continue;
                    }
                }
            }

            buf.append(c);
        }

        return buf.toString();
    }

    /*
     *
     */
    // private static String htmlEscapeFirstAmpersand(String s){
    //
    //  StringBuffer buf = new StringBuffer("&amp;");
    // }

    /*
     *
     */
    public static boolean isNumericHTMLEscapeCode(String s){

        if (s==null || s.length()==0) {
            return false;
        }
        if (!(s.startsWith("&") && s.endsWith(";"))) {
            return false;
        }

        char c = s.charAt(1);
        if (c!='#') {
            return false;
        }

        if (s.length()==3) {
            return false;
        }

        try{
            Integer.parseInt(s.substring(2, s.length()-1));
        }
        catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    /*
     *
     */
    public static boolean isXHTMLEntity(String s){

        if (s==null || s.length()==0) {
            return false;
        }
        if (xhtmlEntities==null) {
            initXHTMLEntities();
        }
        return xhtmlEntities.contains(s);
    }

    /*
     *
     */
    private static void initXHTMLEntities(){

        xhtmlEntities = new HashSet();

        xhtmlEntities.add("&amp;");
        xhtmlEntities.add("&gt;");
        xhtmlEntities.add("&lt;");
        xhtmlEntities.add("&quot;");
        xhtmlEntities.add("&acute;");
        xhtmlEntities.add("&cedil;");
        xhtmlEntities.add("&circ;");
        xhtmlEntities.add("&macr;");
        xhtmlEntities.add("&middot;");
        xhtmlEntities.add("&tilde;");
        xhtmlEntities.add("&uml;");
        xhtmlEntities.add("&Aacute;");
        xhtmlEntities.add("&aacute;");
        xhtmlEntities.add("&Acirc;");
        xhtmlEntities.add("&acirc;");
        xhtmlEntities.add("&AElig;");
        xhtmlEntities.add("&aelig;");
        xhtmlEntities.add("&Agrave;");
        xhtmlEntities.add("&agrave;");
        xhtmlEntities.add("&Aring;");
        xhtmlEntities.add("&aring;");
        xhtmlEntities.add("&Atilde;");
        xhtmlEntities.add("&atilde;");
        xhtmlEntities.add("&Auml;");
        xhtmlEntities.add("&auml;");
        xhtmlEntities.add("&Ccedil;");
        xhtmlEntities.add("&ccedil;");
        xhtmlEntities.add("&Eacute;");
        xhtmlEntities.add("&eacute;");
        xhtmlEntities.add("&Ecirc;");
        xhtmlEntities.add("&ecirc;");
        xhtmlEntities.add("&Egrave;");
        xhtmlEntities.add("&egrave;");
        xhtmlEntities.add("&ETH;");
        xhtmlEntities.add("&eth;");
        xhtmlEntities.add("&Euml;");
        xhtmlEntities.add("&euml;");
        xhtmlEntities.add("&Iacute;");
        xhtmlEntities.add("&iacute;");
        xhtmlEntities.add("&Icirc;");
        xhtmlEntities.add("&icirc;");
        xhtmlEntities.add("&Igrave;");
        xhtmlEntities.add("&igrave;");
        xhtmlEntities.add("&Iuml;");
        xhtmlEntities.add("&iuml;");
        xhtmlEntities.add("&Ntilde;");
        xhtmlEntities.add("&ntilde;");
        xhtmlEntities.add("&Oacute;");
        xhtmlEntities.add("&oacute;");
        xhtmlEntities.add("&Ocirc;");
        xhtmlEntities.add("&ocirc;");
        xhtmlEntities.add("&OElig;");
        xhtmlEntities.add("&oelig;");
        xhtmlEntities.add("&Ograve;");
        xhtmlEntities.add("&ograve;");
        xhtmlEntities.add("&Oslash;");
        xhtmlEntities.add("&oslash;");
        xhtmlEntities.add("&Otilde;");
        xhtmlEntities.add("&otilde;");
        xhtmlEntities.add("&Ouml;");
        xhtmlEntities.add("&ouml;");
        xhtmlEntities.add("&Scaron;");
        xhtmlEntities.add("&scaron;");
        xhtmlEntities.add("&szlig;");
        xhtmlEntities.add("&THORN;");
        xhtmlEntities.add("&thorn;");
        xhtmlEntities.add("&Uacute;");
        xhtmlEntities.add("&uacute;");
        xhtmlEntities.add("&Ucirc;");
        xhtmlEntities.add("&ucirc;");
        xhtmlEntities.add("&Ugrave;");
        xhtmlEntities.add("&ugrave;");
        xhtmlEntities.add("&Uuml;");
        xhtmlEntities.add("&uuml;");
        xhtmlEntities.add("&Yacute;");
        xhtmlEntities.add("&yacute;");
        xhtmlEntities.add("&yuml;");
        xhtmlEntities.add("&Yuml;");
        xhtmlEntities.add("&cent;");
        xhtmlEntities.add("&curren;");
        xhtmlEntities.add("&euro;");
        xhtmlEntities.add("&pound;");
        xhtmlEntities.add("&yen;");
        xhtmlEntities.add("&brvbar;");
        xhtmlEntities.add("&bull;");
        xhtmlEntities.add("&copy;");
        xhtmlEntities.add("&dagger;");
        xhtmlEntities.add("&Dagger;");
        xhtmlEntities.add("&frasl;");
        xhtmlEntities.add("&hellip;");
        xhtmlEntities.add("&iexcl;");
        xhtmlEntities.add("&image;");
        xhtmlEntities.add("&iquest;");
        xhtmlEntities.add("&lrm;");
        xhtmlEntities.add("&mdash;");
        xhtmlEntities.add("&ndash;");
        xhtmlEntities.add("&not;");
        xhtmlEntities.add("&oline;");
        xhtmlEntities.add("&ordf;");
        xhtmlEntities.add("&ordm;");
        xhtmlEntities.add("&para;");
        xhtmlEntities.add("&permil;");
        xhtmlEntities.add("&prime;");
        xhtmlEntities.add("&Prime;");
        xhtmlEntities.add("&real;");
        xhtmlEntities.add("&reg;");
        xhtmlEntities.add("&rlm;");
        xhtmlEntities.add("&sect;");
        xhtmlEntities.add("&shy;");
        xhtmlEntities.add("&sup1;");
        xhtmlEntities.add("&trade;");
        xhtmlEntities.add("&weierp;");
        xhtmlEntities.add("&bdquo;");
        xhtmlEntities.add("&laquo;");
        xhtmlEntities.add("&ldquo;");
        xhtmlEntities.add("&lsaquo;");
        xhtmlEntities.add("&lsquo;");
        xhtmlEntities.add("&raquo;");
        xhtmlEntities.add("&rdquo;");
        xhtmlEntities.add("&rsaquo;");
        xhtmlEntities.add("&rsquo;");
        xhtmlEntities.add("&sbquo;");
        xhtmlEntities.add("&emsp;");
        xhtmlEntities.add("&ensp;");
        xhtmlEntities.add("&nbsp;");
        xhtmlEntities.add("&thinsp;");
        xhtmlEntities.add("&zwj;");
        xhtmlEntities.add("&zwnj;");
        xhtmlEntities.add("&deg;");
        xhtmlEntities.add("&divide;");
        xhtmlEntities.add("&frac12;");
        xhtmlEntities.add("&frac14;");
        xhtmlEntities.add("&frac34;");
        xhtmlEntities.add("&ge;");
        xhtmlEntities.add("&le;");
        xhtmlEntities.add("&minus;");
        xhtmlEntities.add("&sup2;");
        xhtmlEntities.add("&sup3;");
        xhtmlEntities.add("&times;");
        xhtmlEntities.add("&alefsym;");
        xhtmlEntities.add("&and;");
        xhtmlEntities.add("&ang;");
        xhtmlEntities.add("&asymp;");
        xhtmlEntities.add("&cap;");
        xhtmlEntities.add("&cong;");
        xhtmlEntities.add("&cup;");
        xhtmlEntities.add("&empty;");
        xhtmlEntities.add("&equiv;");
        xhtmlEntities.add("&exist;");
        xhtmlEntities.add("&fnof;");
        xhtmlEntities.add("&forall;");
        xhtmlEntities.add("&infin;");
        xhtmlEntities.add("&int;");
        xhtmlEntities.add("&isin;");
        xhtmlEntities.add("&lang;");
        xhtmlEntities.add("&lceil;");
        xhtmlEntities.add("&lfloor;");
        xhtmlEntities.add("&lowast;");
        xhtmlEntities.add("&micro;");
        xhtmlEntities.add("&nabla;");
        xhtmlEntities.add("&ne;");
        xhtmlEntities.add("&ni;");
        xhtmlEntities.add("&notin;");
        xhtmlEntities.add("&nsub;");
        xhtmlEntities.add("&oplus;");
        xhtmlEntities.add("&or;");
        xhtmlEntities.add("&otimes;");
        xhtmlEntities.add("&part;");
        xhtmlEntities.add("&perp;");
        xhtmlEntities.add("&plusmn;");
        xhtmlEntities.add("&prod;");
        xhtmlEntities.add("&prop;");
        xhtmlEntities.add("&radic;");
        xhtmlEntities.add("&rang;");
        xhtmlEntities.add("&rceil;");
        xhtmlEntities.add("&rfloor;");
        xhtmlEntities.add("&sdot;");
        xhtmlEntities.add("&sim;");
        xhtmlEntities.add("&sub;");
        xhtmlEntities.add("&sube;");
        xhtmlEntities.add("&sum;");
        xhtmlEntities.add("&sup;");
        xhtmlEntities.add("&supe;");
        xhtmlEntities.add("&there4;");
        xhtmlEntities.add("&Alpha;");
        xhtmlEntities.add("&alpha;");
        xhtmlEntities.add("&Beta;");
        xhtmlEntities.add("&beta;");
        xhtmlEntities.add("&Chi;");
        xhtmlEntities.add("&chi;");
        xhtmlEntities.add("&Delta;");
        xhtmlEntities.add("&delta;");
        xhtmlEntities.add("&Epsilon;");
        xhtmlEntities.add("&epsilon;");
        xhtmlEntities.add("&Eta;");
        xhtmlEntities.add("&eta;");
        xhtmlEntities.add("&Gamma;");
        xhtmlEntities.add("&gamma;");
        xhtmlEntities.add("&Iota;");
        xhtmlEntities.add("&iota;");
        xhtmlEntities.add("&Kappa;");
        xhtmlEntities.add("&kappa;");
        xhtmlEntities.add("&Lambda;");
        xhtmlEntities.add("&lambda;");
        xhtmlEntities.add("&Mu;");
        xhtmlEntities.add("&mu;");
        xhtmlEntities.add("&Nu;");
        xhtmlEntities.add("&nu;");
        xhtmlEntities.add("&Omega;");
        xhtmlEntities.add("&omega;");
        xhtmlEntities.add("&Omicron;");
        xhtmlEntities.add("&omicron;");
        xhtmlEntities.add("&Phi;");
        xhtmlEntities.add("&phi;");
        xhtmlEntities.add("&Pi;");
        xhtmlEntities.add("&pi;");
        xhtmlEntities.add("&piv;");
        xhtmlEntities.add("&Psi;");
        xhtmlEntities.add("&psi;");
        xhtmlEntities.add("&Rho;");
        xhtmlEntities.add("&rho;");
        xhtmlEntities.add("&Sigma;");
        xhtmlEntities.add("&sigma;");
        xhtmlEntities.add("&sigmaf;");
        xhtmlEntities.add("&Tau;");
        xhtmlEntities.add("&tau;");
        xhtmlEntities.add("&Theta;");
        xhtmlEntities.add("&theta;");
        xhtmlEntities.add("&thetasym;");
        xhtmlEntities.add("&upsih;");
        xhtmlEntities.add("&Upsilon;");
        xhtmlEntities.add("&upsilon;");
        xhtmlEntities.add("&Xi;");
        xhtmlEntities.add("&xi;");
        xhtmlEntities.add("&Zeta;");
        xhtmlEntities.add("&zeta;");
        xhtmlEntities.add("&crarr;");
        xhtmlEntities.add("&darr;");
        xhtmlEntities.add("&dArr;");
        xhtmlEntities.add("&harr;");
        xhtmlEntities.add("&hArr;");
        xhtmlEntities.add("&larr;");
        xhtmlEntities.add("&lArr;");
        xhtmlEntities.add("&rarr;");
        xhtmlEntities.add("&rArr;");
        xhtmlEntities.add("&uarr;");
        xhtmlEntities.add("&uArr;");
        xhtmlEntities.add("&clubs;");
        xhtmlEntities.add("&diams;");
        xhtmlEntities.add("&hearts;");
        xhtmlEntities.add("&spades;");
        xhtmlEntities.add("&loz;");
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean nullString(String str){
        return str == null || str.length() == 0;
    }
}
