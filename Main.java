import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;

    static{
        Locale.setDefault(Locale.US);
    }

    private static final Scanner sc = new Scanner(System.in);

    public interface ImpressoraDLL extends Library {
        ImpressoraDLL INSTANCE = Native.load(
                "C:\\Users\\eduardo_silva02\\Desktop\\Java-Aluno Graduacao\\E1_Impressora01.dll",
                ImpressoraDLL.class);

        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);
        int FechaConexaoImpressora();
        int ImpressaoTexto(String dados, int posicao, int stilo, int tamanho);
        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);
        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);
        int AbreGavetaElgin(int pino, int ti, int tf);
        int AbreGaveta(int pino, int ti, int tf);
        int SinalSonoro(int quantidade, int tempoInicio, int tempoFim);
        int ImprimeXMLSAT(String dados, int param);
        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
        int Corte(int avanco);
        int AvancaPapel(int linhas);

        int InicializaImpressora();
        String GetVersaoDLL();
    }

    public static void configurarConexao() {
        System.out.println("===CONFIGURAÇÃO IMPRESSORA===");
        if(!conexaoAberta){
            System.out.print("---TIPO---\n[1] - USB\nDigite o TIPO: ");
            tipo = sc.nextInt();
            System.out.print("---MODELO---\n[i9] - Impressora\nDigite o MODELO: ");
            modelo = sc.next().toLowerCase();
            System.out.print("---CONEXAO---\n[USB] - Conexao USB\nDigite a CONEXÃO: ");
            conexao = sc.next().toUpperCase();
            System.out.print("---PARAMETRO---\n[0] - Padrão\nDigite o PARÂMETRO: ");
            parametro = sc.nextInt();
            sc.nextLine();
            System.out.printf("""
                ------------------------------------
                Configuração realizada com sucesso!
                Tipo = %d
                Modelo = %s
                Conexão = %s
                Parâmetro = %d%n""",tipo,modelo,conexao,parametro);
        }else{
            System.out.println("Conexão JÁ ESTÁ ABERTA.");
        }
    }

    public static void abrirConexao() {
        System.out.println("===ABRIR CONEXÃO===");
        if(!conexaoAberta){
            int resultado = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo,modelo, conexao, parametro);
            if(resultado == 0){
                System.out.println("Conexão aberta com sucesso!");
                conexaoAberta = true;
            }else{
                System.out.println("ERRO {AbreConexaoImpressora}: " + resultado);
            }
        }else{
            System.out.println("Conexão JÁ ESTÁ aberta!");
        }
    }

    public static void fecharConexao(){
        System.out.println("===FECHAR CONEXÃO===");
        if(conexaoAberta){
            int resultado = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
            if (resultado ==0) {
                System.out.println("Conexão fechada com sucesso!");
                conexaoAberta = false;
            }else{
                System.out.println("Erro em fechar a conexão: "+resultado);
            }
        }else{
            System.out.println("A conexão com a impressora JÁ ESTÁ fechada.");
        }
    }

    public static void impressaoTexto(){
        System.out.println("===IMPRESSÃO DE TEXTO===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            System.out.println("Digite o texto a seguir:");
            String dados = sc.nextLine();
            inicializaImpressora();
            int resultado = ImpressoraDLL.INSTANCE.ImpressaoTexto(dados, 1, 4, 0);
            if(resultado != -4 && resultado != -9999){
                System.out.println("Impressão de texto realizado com sucesso. Quantidade de dados: "+resultado);
            }

        }
    }

    public static void impressaoQRCode() {
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            System.out.println("===IMPRESSÃO DE TEXTO EM QRCode===");
            System.out.println("Digite o texto para QRCode:");
            String dados = sc.nextLine();
            inicializaImpressora();
            int resultado = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, 6,4);
            if (resultado == 0) {
                System.out.println("{ImpressaoQRCode} realizado com sucesso.");
            } else {
                System.out.println("ERRO {ImpressaoQRCode}: " + resultado);
            }

        }
    }

    public static void impressaoCodigoBarras() {
        System.out.println("===IMPRESSAO CODIGO DE BARRAS===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            String dados = "{A012345678912";
            inicializaImpressora();
            int resultado = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, dados, 100,2,3);

            if (resultado == 0) {
                System.out.println("{ImpressaoCódigoBarras} realizado com sucesso.");
            } else {
                System.out.println("ERRO {ImpressaoCodigoBarras}: " + resultado);
            }
        }
    }

    public static void imprimeXMLSAT(){
        System.out.println("===IMPRIMIR XML SAT===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            String dados = "path=C:\\SEU_CAMINHO\\XMLSAT.xml";

            int resultado = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, 0);
            if(resultado == 0){
                System.out.println("Arquivo XML impresso com sucesso.");
            }
            else{
                System.out.println("Erro {ImprimeXMLSAT}: "+ resultado);
            }

        }
    }

    public static void imprimeXMLCancelamentoSAT(){
        System.out.println("===IMPRIMIR XML CANCELAMENTO SAT===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            String dados = "path=C:\\SEU_CAMINHO\\CANC_SAT.xml";
            String assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";
            int resultado = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados,assQRCode,0);
            if(resultado == 0){
                System.out.println("Impressão de XML SAT realizada com sucesso!");
            }else{
                System.out.println("ERRO {ImprimeXMLCancelamentoSAT}: "+resultado);
            }
        }

    }

    public static void abreGavetaElgin(){
        System.out.println("===ABRE GAVETA ELGIN===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            int resultado = ImpressoraDLL.INSTANCE.AbreGavetaElgin(1,50,50);
            if(resultado == 0){
                System.out.println("Gaveta aberta com sucesso!");
            } else {
                System.out.println("ERRO {AbreGavetaElgin}: "+resultado);
            }
        }
    }

    public static void abreGaveta() {
        System.out.println("===ABRE GAVETA===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            int resultado = ImpressoraDLL.INSTANCE.AbreGaveta(1,5,10);

            if (resultado == 0) {
                System.out.println("Gaveta aberta com sucesso!");
            } else {
                System.out.println("ERRO {AbreGaveta}: " + resultado);
            }
        }
    }

    public static void sinalSonoro() {
        System.out.println("===SINAL SONORO===");
        if(!conexaoAberta){
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }else{
            int resultado = ImpressoraDLL.INSTANCE.SinalSonoro(4,5,5);
            if (resultado == 0) {
                System.out.println("SinalSonoro emitido com sucesso.");
            } else {
                System.out.println("ERRO {SinalSonoro}:" + resultado);
            }
        }
    }

    public static void obterVersaoDLL(){
        System.out.println("===VERSÃO DLL===");
        if(conexaoAberta){
            String resultado = ImpressoraDLL.INSTANCE.GetVersaoDLL();
            System.out.println("Versão ATUAL DLL: "+resultado);
        }else{
            System.out.println("Conexão está FECHADA. Execute [Abrir Conexão] primeiro.");
        }
    }

    public static void corte(){
        if(conexaoAberta){
            int resultado = ImpressoraDLL.INSTANCE.Corte(2);
            if (resultado !=0) {
                System.out.println("ERRO {Corte}: " + resultado);
            }
        }
    }

    public static void avancaPapel() {
        if(conexaoAberta){
            int resultado = ImpressoraDLL.INSTANCE.AvancaPapel(2);
            if (resultado != 0) {
                System.out.println("ERRO {AvancaPapel}: " + resultado);
            }
        }
    }

    public static void inicializaImpressora(){
        if(conexaoAberta){
            int resultado = ImpressoraDLL.INSTANCE.InicializaImpressora();
            if(resultado != 0){
                System.out.println("ERRO {InicializaImpressora}: "+resultado);
            }
        }
    }

    public static void main(String[] args){
        while (true) {
            System.out.print("""
                    ************************************
                    * MENU DA IMPRESSORA        *
                    ************************************
                    1 - Configurar Conexão
                    2 - Abrir Conexão
                    3 - Impressão Texto
                    4 - Impressão QRCode
                    5 - Impressão Cod Barras
                    6 - Impressão XML SAT
                    7 - Impressão XML Cancelamento SAT
                    8 - Abrir Gaveta Elgin
                    9 - Abrir Gaveta
                    10 - Sinal Sonoro
                    11 - Obter Versão DLL
                    0 - Fechar Conexão e Sair
                    ************************************
                    Digite sua opção:\s""");
            int opc = sc.nextInt();
            sc.nextLine();
            System.out.println();
            if (opc == 0) {
                fecharConexao();
                break;
            }
            switch (opc) {
                case 1:
                    configurarConexao();
                    break;
                case 2:
                    abrirConexao();
                    break;
                case 3:
                    impressaoTexto();
                    avancaPapel();
                    corte();
                    break;
                case 4:
                    impressaoQRCode();
                    avancaPapel();
                    corte();
                    break;
                case 5:
                    impressaoCodigoBarras();
                    avancaPapel();
                    corte();
                    break;
                case 6:
                    imprimeXMLSAT();
                    avancaPapel();
                    corte();
                    break;
                case 7:
                    imprimeXMLCancelamentoSAT();
                    avancaPapel();
                    corte();
                    break;
                case 8:
                    abreGavetaElgin();
                    break;
                case 9:
                    abreGaveta();
                    break;
                case 10:
                    sinalSonoro();
                    break;
                case 11:
                    obterVersaoDLL();
                    break;
                default:
                    System.out.println("Opção incorreta. Tente novamente!!");
                    break;
            }
            System.out.println();
        }
        System.out.println("VOCE SAIU DO PROGRAMA");
        sc.close();
    }
}