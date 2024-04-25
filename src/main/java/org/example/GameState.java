package org.example;

/**
 * Contain all game states. Five errors states and one win state.
 */
public enum GameState {
    ERRORS_0(" ___________.._______\n" +
                    "| .__________))______|\n" +
                    "| | / /      ||\n" +
                    "| |/ /       ||\n" +
                    "| | /        ||\n" +
                    "| |/         ||\n" +
                    "| |          ||\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "\"\"\"\"\"\"\"\"\"\"|         |\"\"\"|\n" +
                    "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n" +
                    "| |        \\ \\        | |\n" +
                    ": :         \\ \\       : :  \n" +
                    ". .          `'       . ."
    ),
    ERRORS_1(" ___________.._______\n" +
                    "| .__________))______|\n" +
                    "| | / /      ||\n" +
                    "| |/ /       ||\n" +
                    "| | /        ||.-''.\n" +
                    "| |/         |/  _  \\\n" +
                    "| |          ||  `/,|\n" +
                    "| |          (\\\\`_.'\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\\\n" +
                    "\"\"\"\"\"\"\"\"\"\"|         |\"\"\"|\n" +
                    "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n" +
                    "| |        \\ \\        | |\n" +
                    ": :         \\ \\       : :  \n" +
                    ". .          `'       . ."),
    ERRORS_2(" ___________.._______\n" +
                    "| .__________))______|\n" +
                    "| | / /      ||\n" +
                    "| |/ /       ||\n" +
                    "| | /        ||.-''.\n" +
                    "| |/         |/  _  \\\n" +
                    "| |          ||  `/,|\n" +
                    "| |          (\\\\`_.'\n" +
                    "| |         .-`--'.\n" +
                    "| |        /Y     Y\\\n" +
                    "| |       //       \\\\\n" +
                    "| |      //         \\\\\n" +
                    "| |     ')           (`\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "\"\"\"\"\"\"\"\"\"\"|         |\"\"\"|\n" +
                    "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n" +
                    "| |        \\ \\        | |\n" +
                    ": :         \\ \\       : :  \n" +
                    ". .          `'       . ."),
    ERRORS_3(" ___________.._______\n" +
                    "| .__________))______|\n" +
                    "| | / /      ||\n" +
                    "| |/ /       ||\n" +

                    "| | /        ||.-''.\n" +
                    "| |/         |/  _  \\\n" +
                    "| |          ||  `/,|\n" +
                    "| |          (\\\\`_.'\n" +
                    "| |         .-`--'.\n" +
                    "| |        /Y . . Y\\\n" +
                    "| |       // |   | \\\\\n" +
                    "| |      //  | . |  \\\\\n" +
                    "| |     ')   |   |   (`\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "| |\n" +
                    "\"\"\"\"\"\"\"\"\"\"|         |\"\"\"|\n" +
                    "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n" +
                    "| |        \\ \\        | |\n" +
                    ": :         \\ \\       : :  \n" +
                    ". .          `'       . ."),
    ERRORS_4(" ___________.._______\n" +
                    "| .__________))______|\n" +
                    "| | / /      ||\n" +
                    "| |/ /       ||\n" +
                    "| | /        ||.-''.\n" +
                    "| |/         |/  _  \\\n" +
                    "| |          ||  `/,|\n" +
                    "| |          (\\\\`_.'\n" +
                    "| |         .-`--'.\n" +
                    "| |        /Y . . Y\\\n" +
                    "| |       // |   | \\\\\n" +
                    "| |      //  | . |  \\\\\n" +
                    "| |     ')   |   |   (`\n" +
                    "| |          ||'\n" +
                    "| |          || \n" +
                    "| |          || \n" +
                    "| |          || \n" +
                    "| |         / | \n" +
                    "\"\"\"\"\"\"\"\"\"\"|_`-'     |\"\"\"|\n" +
                    "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n" +
                    "| |        \\ \\        | |\n" +
                    ": :         \\ \\       : :  \n" +
                    ". .          `'       . ."),
    ERRORS_5(" ___________.._______\n" +
                    "| .__________))______|\n" +
                    "| | / /      ||\n" +
                    "| |/ /       ||\n" +
                    "| | /        ||.-''.\n" +
                    "| |/         |/  _  \\\n" +
                    "| |          ||  `/,|\n" +
                    "| |          (\\\\`_.'\n" +
                    "| |         .-`--'.\n" +
                    "| |        /Y . . Y\\\n" +
                    "| |       // |   | \\\\\n" +
                    "| |      //  | . |  \\\\\n" +
                    "| |     ')   |   |   (`\n" +
                    "| |          ||'||\n" +
                    "| |          || ||\n" +
                    "| |          || ||\n" +
                    "| |          || ||\n" +
                    "| |         / | | \\\n" +
                    "\"\"\"\"\"\"\"\"\"\"|_`-' `-' |\"\"\"|\n" +
                    "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n" +
                    "| |        \\ \\        | |\n" +
                    ": :         \\ \\       : :  \n" +
                    ". .          `'       . ."),
    WIN("                             ,.        ,.      ,.\n" +
            "                                ||        ||      ||  ()\n" +
            " ,--. ,-. ,.,-.  ,--.,.,-. ,-.  ||-.,.  ,.|| ,-.  ||-.,. ,-. ,.,-.  ,--.\n" +
            "//`-'//-\\\\||/|| //-||||/`'//-\\\\ ||-'||  ||||//-\\\\ ||-'||//-\\\\||/|| ((`-'\n" +
            "||   || |||| ||||  ||||   || || ||  || /|||||| || ||  |||| |||| ||  ``.\n" +
            "\\\\,-.\\\\-//|| || \\\\-||||   \\\\-|| ||  ||//||||\\\\-|| ||  ||\\\\-//|| || ,-.))\n" +
            " `--' `-' `' `'  `-,|`'    `-^-``'  `-' `'`' `-^-``'  `' `-' `' `' `--'\n" +
            "                  //           .--------.\n" +
            "              ,-.//          .: : :  :___`.\n" +
            "              `--'         .'!!:::::  \\\\_\\ `.\n" +
            "                      : . /%O!!::::::::\\\\_\\. \\\n" +
            "                     [\"\"]/%%O!!:::::::::  : . \\\n" +
            "                     |  |%%OO!!::::::::::: : . |\n" +
            "                     |  |%%OO!!:::::::::::::  :|\n" +
            "                     |  |%%OO!!!::::::::::::: :|\n" +
            "            :       .'--`.%%OO!!!:::::::::::: :|\n" +
            "          : .:     /`.__.'\\%%OO!!!::::::::::::/\n" +
            "         :    .   /        \\%OO!!!!::::::::::/\n" +
            "        ,-'``'-. ;          ;%%OO!!!!!!:::::'\n" +
            "        |`-..-'| |   ,--.   |`%%%OO!!!!!!:'\n" +
            "        | .   :| |_.','`.`._|  `%%%OO!%%'\n" +
            "        | . :  | |--'    `--|    `%%%%'\n" +
            "        |`-..-'| ||   | | | |     /__\\`-.\n" +
            "        \\::::::/ ||)|/|)|)|\\|           /\n" +
            "---------`::::'--|._ ~**~ _.|----------( -----------------------\n" +
            "           )(    |  `-..-'  |           \\    ______\n" +
            "           )(    |          |,--.       ____/ /  /\\\\ ,-._.-'\n" +
            "        ,-')('-. |          |\\`;/   .-()___  :  |`.!,-'`'/`-._\n" +
            "       (  '  `  )`-._    _.-'|;,|    `-,    \\_\\__\\`,-'>-.,-._\n" +
            "        `-....-'     ````    `--'      `-._       (`- `-._`-.   hjw")
    ;

    /**
     * ASCII-image that represents game progress.
     */
    private final String view;

    /**
     * Creates state with ASCII-image bounded to them.
     * @param view ASCII-image.
     */
    GameState(String view) {
        this.view = view;
    }

    /**
     * Getter of ASCII-image.
     * @return
     */
    public String getView()
    {return this.view;}
}