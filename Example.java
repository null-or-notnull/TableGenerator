public class Example{
    public static void main(String ...args){
        TableGenerator tg = new TableGenerator("topTitle here");
        tg.setTitles("title0 here","title1 here","title... here");
        try{
            tg.addData("data0 here","data0 here","data... here");
            tg.addData("data0 here","data0 here","data... here");
            tg.addData("... here","... here","... here");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        System.out.print(tg.generateTable());
    }
}