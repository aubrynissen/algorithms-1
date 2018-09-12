/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class HelloWorld {
    public static void main(String[] args) {
        int lgth = args.length;
        int rmndr = lgth % 2;
        int grtgs = lgth - rmndr;

        //System.out.println(Integer.toString(lgth) + "arguments");
        //System.out.println(Integer.toString(rmndr) + "over an even number");
        //System.out.println(Integer.toString(grtgs) + "complete arguments");

        for (int i = 0; i < grtgs; i += 2) {


            //System.out.println(
            //        Integer.toString(i + 1) + "of" + Integer.toString(grtgs)
            //                + "complete arguments");

            String count = args[i];
            int cnt = Integer.parseInt(count);

            String str2 = args[i + 1];

            for (int x = 0; x < cnt; x++) {

                //System.out.println(Integer.toString(x + 1) + "of" + Integer.toString(cnt)
                //                           + "repetitions of his greeting");

                if (str2.equals("Hello")) {
                    System.out.println("Hello, you big beautiful world!");
                }
                else if (str2.equals("Hi")) {
                    System.out.println("Hi yourself, world-o!");
                }

            }

        }


    }
}
