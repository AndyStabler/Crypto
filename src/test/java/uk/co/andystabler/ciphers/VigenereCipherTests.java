package uk.co.andystabler.ciphers;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class VigenereCipherTests {

    public static class encrypt {

        @Test
        public void emptyString_ReturnsEmptyString() {
            String plaintext = "";
            String key = "WILDE";
            String expected = "";
            Assert.assertEquals(expected, VigenereCipher.encrypt(plaintext, key));
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyKey_IllegalArgumentException() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            String key = "";
            VigenereCipher.encrypt(plaintext, key);
        }

        @Test
        public void validKey_encrypts() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            String key = "WILDE";
            String expected = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            Assert.assertEquals(expected, VigenereCipher.encrypt(plaintext, key));
        }

        @Test
        public void mixedCasePlaintext_EncryptCorrect() {
            String plaintext = "THeTRuTHISRARELYPUREANDneverSImPLE";
            String key = "WILDE";
            String expected = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            Assert.assertEquals(expected, VigenereCipher.encrypt(plaintext, key));
        }
    }

    public static class decrypt {

        @Test
        public void emptyString_ReturnsEmptyString() {
            String ciphertext = "";
            String key = "WILDE";
            String expected = "";
            Assert.assertEquals(expected, VigenereCipher.encrypt(ciphertext, key));
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyKey_IllegalArgumentException() {
            String plaintext = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            String key = "";
            VigenereCipher.encrypt(plaintext, key);
        }

        @Test
        public void validKey_decrypts() {
            String plaintext = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            String key = "WILDE";
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, VigenereCipher.decrypt(plaintext, key));
        }

        @Test
        public void mixedCaseCiphertext_DecryptCorrect() {
            String ciphertext = "PPPWVqBSLWNIcHPUXFUIWVOQIRMCVMIXWH";
            String key = "WILDE";
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, VigenereCipher.decrypt(ciphertext, key));
        }
    }

    public static class calculateKeyLen {

        @Test
        public void KeyLen3() {
            String text = "vhx tugpigi kxa vttitpt hh tag vbiegète vkpagr pcs tnsh eoguiwgrxf ugdrxcktdlx ct hpe mkmx. vhbu vxtsbqn nuel cs mje dgy t dlhek hh txzt tu lhpg tu tag pecigveqv. tag pkqbegm pkta vhx tugpigi kxa vbiegète vkpagr bu tact mje vtyivagclrut acs lvamksmkctn ighokoamkog cbhwt mje dgy (tusnoigi tact mje unovm oy veqv il kn t mnhyn ecnzwazg) agf tact bpfhtmtvihp wbnl ug rxhlxetxf ig vhx eiijekveqv. iy wsbpg t mer yhbeh bu tkwlr tagfof, ks tv lxcsm cs eqnz cs mje xpckapmgd fgslcgx cnw ks nuew qnea ogee, mje okgxpèrx eiijek ks mjehtemkctnlr wnutetmaune. aqwxxek, kn mjil ealg im ks mje dgy, gqt mje vkpagr, pjivj pkqvbfel errrthirtrhbe smtegita cnw uuvj srutxos tte itoigrea rxhektew vo vqlegcmkvxny tu ogg-tboe icd lasmgml, krkgsigcmkvx qf pjivj cbrhxts tte xopeqyxf.";
            int len = VigenereCipher.calculateBestGuessKeyLen(text);
            Assert.assertEquals(3, len);
        }

        @Test
        public void keyLen4() {
            String text = "mve unbnlgu khr jaubonw ht tkx jijxbèrh vwpkxf wdl olvh qoqlwdhksd xgprhtyaees aw hbe wbae. waws yxfslhb uvxg av mve nxm a eeccn ht thqh av ecnj tg tkx dldbbthqh. tkx drruzep pwtk mve unbnlgu khr jijxbèrh vwpkxf iv mvaw mve fkmpwtbaorgt ktg swthivmwcde wnihfmdmwoq tpoxm hhh dsy (dlgupbbg waot was bohqk ry heam ws lg o kqhkn otbgxtue) dgr tkth iqycrpthirg kioe pe uxtlhvheg bb tkx qisasrwxlt. ly islgu a nxm wkbqh ll hrxem rdgrop, bg aw esavm os ohbg dl hhh xbcurdthw aevlogh tbd ll ishw cnor cnfx, hhh owghgèfe fbdhhk ws wasouxhiftzlb nbbuxokduze. khkeyxf, iq mviv vosh bh iv mve nxm, nrm hhh vwpkxf, wkbqh skcvlwss fkmpwhurdivif lhrhgutk tbd vnqh vrgthfg aux drrisror feixfrhw ho fhzlhvhiyxzy dl cnh-mwmh iod vrgthfg, iuksssxqtlos oi pvifa qisasrv tfe hfdlrrsd.";
            int len = VigenereCipher.calculateBestGuessKeyLen(text);
            Assert.assertEquals(4, len);
        }

        @Test
        public void KeyLen5() {
            String text = "avv jyubzfk rsp neywrfx vt kzi cwxwrèys tatosi oez ocks jcekmksiwh bbsjihyrtpl ok grl hzei. avzk zlfjasu ijww hg kzi rsp s fsctc sm hvpx hg cgrn oj lll dcsmuhvpx. avv hvvpcwq dwkz xos imruwey olm maklbèiw gpdywv pg kzea hyw gymgleuocqwa vrk waokawawtsp pbwgvtokasu osgya hyw olm (rkwbazfk avrl xos sdsjy fx xllk aw pb r crvke deuulskl) oev xook armcieeawff apzc ti yswdijhvv mu hyw gpdywvasol. mm ijarn o bwc dvzul pg kjysm isrkcd, aw hh cwezh rk pvbx sw avv wrjfphxlr dwwzoxw eur zk yzsu grsm ffgl, hyw zpuvfèvl qzhllf zk xosfjiawtspsm lffysrceizv. zsdsmwv, pb kzmz qrki ph zk xos bwc, uck lll qzhllf, nzmjv gjscwuww jfphxvuistowt kxyseyxo oev wbqy kczhvew hfv hvvdvjpf fvxiyfvv xv qfdplqkazlzp sw vbv-lmts gsh zmjlitg, zjvlggwgawmw sm kyago qzhllfj svl sdhpvmvv.";
            int len = VigenereCipher.calculateBestGuessKeyLen(text);
            Assert.assertEquals(5, len);
        }

        @Test
        public void uniformDistributionOfCharacters_Minus1() {
            String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int len = VigenereCipher.calculateBestGuessKeyLen(text);
            Assert.assertEquals(-1, len);
        }
    }

    public static class calculateKey {

        @Test
        public void keyLength3LongText() {
            String text = "Hak qum kn mje npcacrmgd uccdyamgrl qf mje npftuhbqntdlx  gnw  qf\n" +
                    "mje  pgsmgrg  upbtae  crf  qf  mje Zcltzy ekel c sfcle wnkggttdxf\n" +
                    "yxnlhy snp.\n" +
                    "\n" +
                    "Okdimknz vhbu am c dbuttpcx qf kquzjlr piggtr-vwh  oienihp  mbnel\n" +
                    "ks  tp  umvekny bpsbinbhivcnm nimvlx dlng gkgeg rltpem yhhue tre-\n" +
                    "wgsvgnwgd ekfx hokos tte lq afczbpgea pkkmbviog tact  mjer  utbnl\n" +
                    "mjigm dbiimcl pctvjel crx c pkgtma nxct bfet.\n" +
                    "\n" +
                    "Vhbu pecnxv htu - ok tamjek jaw - c pkqbegm, pjivj wtu taks: fqsm\n" +
                    "qf  mje  igoine  hp  im yekg ugjairy yqr itemvy fwca qf mje mkmx.\n" +
                    "Oaga shnumkogu wxte lwgzgsmgd yqr mjil rrhdlxo, bnv mhut hh tagsx\n" +
                    "yekg  lttgxny  vqnvgrggd pkta vhx ooogmxptl qf loaen gkgeg rixeel\n" +
                    "qf icpxt, wakca ks hfd ugctwsx qn mje pjoeg im yalp't  mje  loaen\n" +
                    "gkgeg rixeel qf icpxt tact pgrx wnacpia.\n" +
                    "\n" +
                    "Agf sh vhx rrhdlxo rxoabpew; nomu oy vhx rehrlx  yekg  mxcn,  tpd\n" +
                    "fqsm qf mjef yekg mbuekcbeg, eogn mje hpel yimj dbiimcl pctvjel.\n" +
                    "\n" +
                    "Oaga wxte bpckgalknzny hh tag oiknbqn mjam vhxa'd tnl fcdx c  bbi\n" +
                    "mbuttme  bp  choigi  dhyn  ytof vhx vrxgs bp tag fbtsm rltee. Tpd\n" +
                    "lqmx uabf tact xxeg vhx vrxgs acd ugeg c btf mhxe,  tpd  mjam  po\n" +
                    "hpe ljonnd xxek jaog lxht mje heetps.\n" +
                    "\n" +
                    "Tpd mjeg, qnx Vhntswcy, ggakny myo mjonuagf yxcrl cfmgr  hpe  fcn\n" +
                    "acd  ugeg pabnew vo t vrxg fht staigi hhy gkgam kt pquef bx vo ug\n" +
                    "nbee mq pxqpeg fht a vjagie, hpe zkre uimvigi og  jek  qwg  kn  t\n" +
                    "umtnl  vcfx  kn  Kkcdoaguwhtta uuwfegny kgaekzxf wact bv wtu tact\n" +
                    "acd ugeg iobpg ptogi aen taks mkmx, cnw uhx higclea kggw aqw  mje\n" +
                    "pqref  chwlw  de  fcdx  c  ghqd tpd acpia peccx. Vhbu tboe bv wtu\n" +
                    "rbihm, kt pquef whtk, tpd gq ogg whwlw  jaog  th  iem  pabnew  vo\n" +
                    "tpymjigi.";
            text = text.replaceAll("[^a-zA-Z]", "");

            String key = "CAT";
            Assert.assertEquals(key, VigenereCipher.calculateKey(text));
        }

        @Test
        public void keyLength5LongText() {
            String text = "px hlg h fctuox nzzk hlj wu eacws, eyo hoi nwcjod hsyi defpotyu altchliy. hwuwezb zqtev, " +
                    "omd nvpr yfngppo wuxz swz fcpozx ty ou iqqcyx ez szglas alp gwsi htbk, wwtdwio bipgvwm alczinl " +
                    "ess  npldg kszcg vj gtqascj ahrdtcuw, escbks yca uftqrpj pbvyrs hv tcpjlre l gdmcw cm kcthac " +
                    "ofga  jcza lrepfprr lzvrr hwal sta. alp sosphlm zqpwh vj mzwsio noiflrs hro zzk vlr ahxd. lh vrp" +
                    "  pbk sq th h gzwcbvpo dvwepf, asz woykp qcy myocvv otgwplj, vhh mpsu xlnylh ez hoi hlzs. me  " +
                    "oswmnesk wtxdsc ly suscxcbw qlql, qzcs ally o tiecs dmop: hoi qlql sq l ahr zq oisfe tvvej-tpzp," +
                    "  hwal l sshzj mzhgv xcbwelqoi lyr yyrrskpj souhdzal jplhbvpd. kprdecu qlos msc evl welwyw. te " +
                    "khw  yz izi ecmprr evl ptqh. lzpy oa xsp plwe zt amxpg px hlg ziwoct azcyprr, lbk ee aflwpyh  " +
                    "alp pzlgecwj gfcflre hoz gfe cmj offprr oofptrva lzffz. me hoz tlch vj ess lgzyctc ocwci ty  " +
                    "dyialfhxtzb msc soai hpsr. xsp tsee hoz wpgsu jwtuoxd fd, hro hwuwezb, dlz hoz xstfac-ytbl eyo  " +
                    "vhh l goymnzgl ywnsy emzjl ltd fpkse ouowp, klre dzvawj, flwetbn wpgsyew ewtid zb alp hof. sy  " +
                    "pojl wlbkmyr, cwtzdwai ess smqe-goeqe, hoi azgaic hwal ess lrzcavyd qoji rlnlh qcct xsp khpw.  " +
                    "th ded zbl sq evvwp awjxfcsz astqo ecp gv gzyhymgpr alle hoi pjsz jzwzva jzi hfzfh dlpy mvy xzjl" +
                    ".  ftr pysessy md hoagstbn czf, hoi nldamzy plrplho me cou. mydwki ess mple o mvfthf zztql ald  " +
                    "fleotbn sfe o smde cm jtriyid hvpgs sok wzxsaltyu as oz kpxs evl tczrbgetcu sq awn-mczb. alp " +
                    "gcpgp  noti qcct ey zpssyr alxlw dsebfs smvp o kywwsk qtcfvv hswjl qzftio aoyx zq hoi dffmenp  " +
                    "cm xsp fpkse-vhro hosp. htbzxzy hbvypr h whthjl lyr alp gcpgp douo dzalaslh, alzfuo xsp kvvod  " +
                    "klvp dhppw owzxtyubmdsoipp. evl mydhyyxpba (xsp hlppdqyipy, wa ald qhpwpr) jsfwr ii otatio, mia " +
                    " xspfl ald bv alj cm wsfhamyr wa sqq qvqawsaiwj. vl qzgsk sgpf as ess dmyocd: e dxosptdv, mvltz " +
                    " mmrffl, xsp alercsuidd cm ltd pvhj xsyiwj sttslgpdpo pf xsp psyp zjlvlwzz astqo apcs alp " +
                    "fbpjzca  vj ess wecem. omd sopv hlg cicj thmc, swz jlns ueeffhpwj ghrrfwui, stg zoty fvyrssuio  " +
                    "mm jslcgl wzld hro mzbre cogsc mzhhpd ouh ess jswo cm xsp kprepf alle vhh ufga iyosk.";
            text = text.replaceAll("[^a-zA-Z]", "");

            String key = "HELLO";
            Assert.assertEquals(key, VigenereCipher.calculateKey(text));

        }

        @Test
        public void keyLength10LongText() {
            String text = "ticvw nmfewp vzxfklx fawm elc tcjn gr bsi dckzfq. gvnpc xcihgz elw jcpxy\n" +
                    "szl yiamjvmk, iqel yp ceijywfw znyte egaeeajc; roff xpxspgr qse\n" +
                    "pzvqg-drwwp iyh zqlp; xmptpc ucq sfgzl, amlm, yex hazvc. fcpis, gz bsi\n" +
                    "mvfvl zmvo, ayu qdudx iyh qmgehq, iqel ztgcfamve kpgce yqqa lrb\n" +
                    "lck-vdmkv lykp kbsf elw ynursk gvemba. fv qgdm csspb xfseapw, ypb fh zua\n" +
                    "qspgfvuv iid e rjge, faspergpe-jbsbmo wacp. zn oma elgu qtuj fplx kcbv\n" +
                    "bsdzj wm ryinaocwepnw lhmeclp, cxce zgd i hmxcpu. nzua dgyt urm ltm zrja\n" +
                    "fzhl an septw'j pwdg xcqvcicgga aeqv, mw nzq zpeqql yy zml micp jvzl av\n" +
                    "elc fsimdqgd' hmqpjnwb mwitgl pysda midqpv. ul fpp eeg mw ifq gpep qju,\n" +
                    "bsdzj lyf qfgwtwh wsttzpwp i nypuc wlgy bsi etcrnweb oepm qfluqzpv mh\n" +
                    "ycf luup, pmtb midpmxspv, uyikq vlqc omjn oubnlcu yex ouhlvbu qkcdx\n" +
                    "npepgb ki kbmlo. fcpis'k bicilvq yuv pqph gp tffvquzvr'u yknsos, myr\n" +
                    "jyilq tio iqeygyv iqel fkq ccytbymli qtuj, mvo wmocyio - zwmsba\n" +
                    "sexwdaesmf uys natoikqpk'm haepvq jyu vwqv oiqvpfswp bsi gpqkuff pp lyf\n" +
                    "drcdql es ikjc bsdzj. wm jyilq tio fcgl slggosx sr zp bae lpeb omkbwd'a\n" +
                    "dmqvci ufp ppv fwqsufp. pp lyf qgyff bpr wgyim oubs xfg bllkxmjw, lgtvl\n" +
                    "mzlpvqvyexazo hlw jc byhf ulogpe fxv fptreu frjhqv hmrjmln eqiymli rf,\n" +
                    "vwxqpzgpe kbw pccwjgwj' mlazj xfcr yy zml rsr jgj mumz tr rjc tuj ozlwf\n" +
                    "vfrn zml vmjncu bae xlvcprj. ufp bsil, gvrwlxg l cccp rag, twraytrj bsp\n" +
                    "ecmrvce ng ticvw, clu nzq essjg qkijk plh aqkv imf. plvpa frx lmspr sr\n" +
                    "fzm hxini yv uztsdl dgfqmc, qzqzp lc clu bae anep yciy xmuzyq ... dsk\n" +
                    "hgi bsi qeffid kmlv ucq fpwd, iyh fg urm tmkv agvf kbw pccwjgwj zgd bsi\n" +
                    "qwkdyj, nino rq zvcfs bciyvcu fawm l hmi ryul tio vmnjvx az azqcvfzhy\n" +
                    "euppja.";
            text = text.replaceAll("[^a-zA-Z]","");
            String key = "MILEYCYRUS";
            Assert.assertEquals(key, VigenereCipher.calculateKey(text));
        }

        /**
         * Our method that uses the index of coincidence as the indicator of whether the key length is correct only
         * really works for long strings!
         */
        @Test
        @Ignore
        public void keyLength3ShortText() {
            String text = "vhx vrnvh bu rtteea pnte tpd ggvxt sbopeg. tagsx yekg tag whtdl qf huctt wbndx.";
            text = text.replaceAll("[^a-zA-Z]", "");
            String key = "CAT";
            Assert.assertEquals(key, VigenereCipher.calculateKey(text));
        }
    }

}