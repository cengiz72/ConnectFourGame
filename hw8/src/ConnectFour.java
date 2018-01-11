import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author cengo
 */
public class ConnectFour {
    private int size;
    private int order_who = 1; //1 user1 and 0 user2;
    private  String gameType;
    private Cell arr[][];
    private JButton [][] button;
    private void makeTheAllButtonsAndCellsToFirstState(){
        for (int i=0; i < getSize(); ++i) {
        
            for (int j=0; j < getSize(); ++j){
                button[i][j].setIcon(null);
                arr[i][j].set_PlayerType(".");
            }
        }
        order_who = 1;
    }
    private  class abc implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i=0; i < getSize(); ++i) {
                
                for (int j=0; j < getSize(); ++j) {
                      if (e.getSource() == button[i][j]) {
                        if (gameType.equalsIgnoreCase("P")) {
                            if (order_who == 1) {
                                if (move("r1.png","X",j)) {
                                   JOptionPane.showMessageDialog(null, "User 1 win", "Who The win", JOptionPane.PLAIN_MESSAGE);
                                   makeTheAllButtonsAndCellsToFirstState();
                                }
                            }
                        else if(order_who == 0) {     
                            if (move("b2.png","O",j)) {

                               JOptionPane.showMessageDialog(null, "User 2 win", "Who The win", JOptionPane.PLAIN_MESSAGE);
                               makeTheAllButtonsAndCellsToFirstState();
                            }
                        }
                      }
                         
                      else if (gameType.equalsIgnoreCase("C")){
                          if(order_who == 1) {
                            if(move("r1.png","X",j)) {
                            
                                JOptionPane.showMessageDialog(null, "User  win", "Who The win", JOptionPane.PLAIN_MESSAGE);
                                makeTheAllButtonsAndCellsToFirstState();
                            }
                          }
                         }
                        
                    }
                }
                
            }
                         
        if (gameType.equalsIgnoreCase("C")){

            if(order_who == 0) {

              if(moveOfComputer("b2.png","O")) {

                JOptionPane.showMessageDialog(null, "PC win", "Who The win", JOptionPane.PLAIN_MESSAGE);
                makeTheAllButtonsAndCellsToFirstState();
                
              }
              else order_who = 1;
            }
           }
        }
    };
    private JPanel panel = new JPanel();
    private JPanel temp = new JPanel();
    private JFrame frame = new JFrame("Game");
    public ConnectFour() { size = 4;}
    public int getSize() {return size;}

    /**
     * this function set size and create jrame,jPanel and
     * put the Jpanel and Jbutton into Jframe according to size
     * @param size size width and height of board
     * @throws Exception if size < 0
     */
    public void setSize(int size) throws Exception {
        if (size >= 4) {
            this.size=size;
            JLabel label1 = new JLabel();
            JLabel label2 = new JLabel();
            JLabel label3 = new JLabel();
            Image image1 = ImageIO.read(getClass().getResource("r1.png"));
            Image image2 = ImageIO.read(getClass().getResource("b2.png"));
            Image image3 = ImageIO.read(getClass().getResource("yellow.png"));
            button = new JButton[size][size];
            frame.setVisible(true);
            if (size > 9 && size < 17){
                frame.setSize((size+3)*30, (size+5)*30);
                panel.setSize((size+3)*30,(size+5)*30);
            }
            else if (size >= 17) {
                frame.setSize((size+4)*30, (size+5)*30);
                panel.setSize((size+4)*30,(size+5)*30);
            
            }
            else {
                frame.setSize((size+2)*30, (size+5)*30);
                panel.setSize((size+2)*30,(size+5)*30);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            arr = new Cell[size][size];
            for (int i = 0; i < size; i++){
               arr[i] = new Cell[size];
            }
           
            for (int i = 0; i < size; i++) {
               for (int j = 0; j < size; j++){
                   arr[i][j] = new Cell(i,j,"."); 
               }
            }
            for (int i = 0; i < size; i++) {
                
                for (int j = 0; j < size; ++j) {
                    //Image image = ImageIO.read(getClass().getResource("r1.png"));
                    button[i][j] = new JButton();
                    button[i][j].setPreferredSize(new Dimension(30, 30));
                    button[i][j].addActionListener(new abc());
                    //button[i].setIcon(new ImageIcon(image));
                    panel.add(button[i][j]);                

                }

            }
            //panel.setVisible(true);
            panel.setBackground(Color.ORANGE);
            frame.add(panel);
            frame.setResizable(false);
            if (gameType.equalsIgnoreCase("P")) {
                
                label1.setText("Player 1");
                label2.setText("Player 2");
                label3.setText("Winner ");
                label1.setIcon(new ImageIcon(image1));
                label2.setIcon(new ImageIcon(image2));
                label3.setIcon(new ImageIcon(image3));
                temp.setPreferredSize(new Dimension((size+2)*30,40));
                temp.add(label1);
                temp.add(label2);
                temp.add(label3);
                temp.setVisible(true);
                panel.add(temp);
            }
            if (gameType.equalsIgnoreCase("C")) {
                
                label1.setText("Player");
                label2.setText("Computer");
                label3.setText("Winner ");
                label1.setIcon(new ImageIcon(image1));
                label2.setIcon(new ImageIcon(image2));
                label3.setIcon(new ImageIcon(image3));
                temp.setPreferredSize(new Dimension((size+2)*30,40));
                temp.add(label1);
                temp.add(label2);
                temp.add(label3);
                temp.setVisible(true);
                panel.add(temp);            
            
            }
            arr = new Cell[size][size];
            for (int i = 0; i < size; i++)
               arr[i] = new Cell[size];
           
            for (int i = 0; i < size; i++) {
               for (int j = 0; j < size; j++)
                   arr[i][j] = new Cell(i,j,".");
            }
        }
        else  throw new  Exception("size must be greater than four.");
    }
    /**
     * 
     * @param filename icon name(red or blue)
     * @param symbol   type of player for Cell (X or O)
     * @param j        column of Cell
     * @return true if four cells matches
     */
    private boolean move(String filename,String symbol,int j){
    
        if (symbol.equals("X")) {
          if (order_who == 1) {

               if (getRowOfAvaiable(j, ".") != -1) {
                   int row = getRowOfAvaiable(j, ".");
                   arr[row][j].set_PlayerType(symbol);
                   try {
                        Image image = ImageIO.read(getClass().getResource(filename));
                        button[row][j].setIcon(new ImageIcon(image));

                       }


                    catch (IOException ex) {
                        Logger.getLogger(ConnectFour.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (determineTheWinWho(symbol,row,j , 1)){
                        return true;
                    }
                    order_who = 0;

             }

            }
        }
        else {
                if (getRowOfAvaiable(j, ".") != -1) {
                   int row = getRowOfAvaiable(j, ".");
                   System.out.println(row);
                   arr[row][j].set_PlayerType(symbol);
                   try {
                        Image image = ImageIO.read(getClass().getResource(filename));
                        button[row][j].setIcon(new ImageIcon(image));

                        }


                    catch (IOException ex) {
                        Logger.getLogger(ConnectFour.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (determineTheWinWho(symbol,row,j , 1)){
                        return true;
                    }
                    order_who = 1;

                }

   }
         return false; 
    }
    /**
     *
     * @param xCoordinate x-axis of Cell
     * @param yCoordinate y-axis of Cell
     * @return true if cell is inside border of board.
     */
    public boolean isCellLegal(int xCoordinate,int yCoordinate){
        if (xCoordinate >= 0 && xCoordinate < getSize() && yCoordinate >=0 && yCoordinate < getSize())
              return true;
        else  return false;   
    }
    /**
     * 
     * @param row
     * @param column
     * @param down
     * @param left
     * @param right
     * @param left_up
     * @param right_down
     * @param right_up
     * @param left_down
     * @param symbol 
     */
    private void tolowerFourLettersMatching (int row,int column,int down,int left,int right,
                                    int left_up,int right_down,int right_up,int left_down,String symbol) {
    Image image = null;
    try {
        
          image = ImageIO.read(getClass().getResource("yellow.png"));
        
    }
     catch (IOException ex) {
         Logger.getLogger(ConnectFour.class.getName()).log(Level.SEVERE, null, ex);
     }
    
    if (left+right>=3) {
        for (int i = 0; i <= right; ++i){
             arr[row][column+i].set_PlayerType(symbol.toLowerCase());
             button[row][column+i].setIcon(new ImageIcon(image));
        }
        for (int i = 0; i <= left; ++i){
            arr[row][column-i].set_PlayerType(symbol.toLowerCase());
           button[row][column-i].setIcon(new ImageIcon(image));
        }
    }

    else if(down>=3) {
        for (int i = 0; i <= down; ++i){
            System.out.printf("row : %d\n", row);
            arr[row+i][column].set_PlayerType(symbol.toLowerCase());
            button[row + i][column].setIcon(new ImageIcon(image));
        }
    }

    else if (left_up+right_down>=3) {
        for (int i = 0; i <= left_up; ++i) {
            arr[row-i][column-i].set_PlayerType(symbol.toLowerCase());
            button[row -i][column-i].setIcon(new ImageIcon(image));
        }
        for (int i = 0; i <= right_down; ++i) {
            arr[row+i][column+i].set_PlayerType(symbol.toLowerCase());
             button[row + i][column+i].setIcon(new ImageIcon(image));
        }
    }

    else if (left_down+right_up>=3) {
 
        for (int i = 0; i <= left_down; ++i) {
            arr[row+i][column-i].set_PlayerType(symbol.toLowerCase());
             button[row + i][column - i].setIcon(new ImageIcon(image));
        }
        for (int i = 0; i <= right_up; ++i) {
            arr[row-i][column+i].set_PlayerType(symbol.toLowerCase());
            button[row - i][column+i].setIcon(new ImageIcon(image));
        }
    }

}
   
   /**
    * 
    * @param currentRow x-axis of cell
    * @param symbol     type of player for cell
    * @param value      for if four cells matching convert to lowercase or not.(value=1 convert lowercase
    *                   value=0 not convert to lowercase)
    * @param column     y - axis of cell
    * @return   true if four cell mathcs
    * check the from left-up to right -down diagonal for finding four mathcing cells.
    */
    private boolean findTheConnectFour(int currentRow,String symbol,int value,int column) {
        boolean result=false;
        boolean flag=true;
        int countOfLeftToUpMathcingCells=0;
        int countOfRihgtToDownMatchingCells=0;
        //first,it is scaned left-up board from current cell.
        for (int i = 1,j=1; i < 4 && j<=3 && flag; ++i,++j) {
            if (isCellLegal(currentRow-i,column-j)) {

                if (arr[currentRow-i][column-j].get_player_type().equals(symbol)) ++countOfLeftToUpMathcingCells;
                else flag=false;
            }
            else break;
        }
        flag=true;
        //then,it is scanned right-down from current cell.
        for (int i = 1,j=1; i < 4 && j<=3 && flag; ++i,++j) {
            if (isCellLegal(currentRow+i,column+j)) {

                if (arr[currentRow+i][column+j].get_player_type().equals(symbol)) ++countOfRihgtToDownMatchingCells;
                else flag=false;
            }
            else break;

        }
        if (countOfRihgtToDownMatchingCells+countOfLeftToUpMathcingCells>=3) {
            if (value==1)
            //Finding four matching cells converts to lowercase.
            tolowerFourLettersMatching(currentRow,column,0,0,0,countOfLeftToUpMathcingCells,
                                                 countOfRihgtToDownMatchingCells,0,0,symbol);
            result=true;
        }
        return result;
}
/**
 * 
 * @param symbol type of player for cell
 * @param currentRow x-axis for cell
    * @param value      for if four cells matching convert to lowercase or not.(value=1 convert lowercase
    *                   value=0 not convert to lowercase)     
 * @param column y -axis of cell
 * @return  true if four cell mathcs
 * //check the down vertical in order to find four mathcing cells
 */
    private boolean findTheConnectFour(String symbol,int currentRow,int value,int column) {
        boolean result=false;
        boolean flag=true;
        int counterMatchingCells=0;

        for (int i = currentRow+1; i<= currentRow+3 && flag; ++i){

            if(isCellLegal(i,column)) {
                if (arr[i][column].get_player_type().equals(symbol)) counterMatchingCells=counterMatchingCells+1;
                else flag=false;

            }
            else break;

        }
        if (counterMatchingCells>=3) {
            if (value==1)
            tolowerFourLettersMatching(currentRow,column,counterMatchingCells,0,0,0,0,0,0,symbol);
            result=true;
        }

        return result;
}

/**
 * 
 * @param symbol type of player for cell
 * @param currentRow x-axis for cell
 * @param value      for if four cells matching convert to lowercase or not.(value=1 convert lowercase
 *                   value=0 not convert to lowercase)     
 * @param column y -axis of cell
 * @return  true if four cell mathcs
 * //check the from left-down to right-up diagonal in order to find four mathcing cells
 */
    private boolean findTheConnectFour(int value,int column,String symbol,int currentRow) {
        boolean result=false;
        boolean flag=true;
        int countOfLeftToDownMatchingCells=0;
        int countOfRightToUpMatchingCells=0;
        for (int i = 1,j=1; i < 4 && j<=3 && flag; ++i,++j) {
            if (isCellLegal(currentRow+i,column-j)) {

                if (arr[currentRow+i][column-j].get_player_type().equals(symbol)) ++countOfLeftToDownMatchingCells;
                else flag=false;
            }
            else break;
        }
         flag=true;
        for (int i = 1,j=1; i < 4 && j<=3 && flag; ++i,++j) {
            if (isCellLegal(currentRow-i,column+j)) {

                if (arr[currentRow-i][column+j].get_player_type().equals(symbol)) 
                    ++countOfRightToUpMatchingCells;
                else flag=false;
            }
            else break;
        }
        if (countOfRightToUpMatchingCells+countOfLeftToDownMatchingCells>=3) {
            if (value==1)
            tolowerFourLettersMatching(currentRow,column,0,0,0,0,0,
            countOfRightToUpMatchingCells,countOfLeftToDownMatchingCells,symbol);

            result=true;
        }

        return result;
}
    /**
 * 
 * @param symbol type of player for cell
 * @param currentRow x-axis for cell
 * @param value      for if four cells matching convert to lowercase or not.(value=1 convert lowercase
 *                   value=0 not convert to lowercase)     
 * @param column y -axis of cell
 * @return  true if four cell mathcs
 * //check the lefthand-righthand horizontal in order to find matching cells.
 */
    private boolean findTheConnectFour(int currentRow,int value,int column,String symbol) {
        boolean result=false;
        boolean flag=true;
        int countOfLeftSideMatchingCells=0;
        int countOfRightSideMatchingCells=0;
        for (int i = column-1; i >= column-3 && flag; --i) {
            if (isCellLegal(currentRow,i)) {
                if (arr[currentRow][i].get_player_type().equals(symbol)) 
                    ++countOfLeftSideMatchingCells;
                else flag=false;
            }
            else break;
        }
        flag=true;
        for (int i = column+1; i <= column+3 && flag; ++i) {
            if (isCellLegal(currentRow,i)) {
                if (arr[currentRow][i].get_player_type().equals(symbol)) ++countOfRightSideMatchingCells;
                else flag=false;
            }
            else break;
        }   
        if (countOfLeftSideMatchingCells+countOfRightSideMatchingCells>=3) {
            if(value==1)
            tolowerFourLettersMatching(currentRow,column,0,countOfLeftSideMatchingCells,
                                      countOfRightSideMatchingCells,0,0,0,0,symbol);
            result=true;
        }

        return result;
}
/**
 * this fucntion check the equality state.
 * @return true if empty cell does not exist
 */
    private  boolean checkEmptyIsStillExisting() {
    for (int i=0; i<getSize(); ++i)
        for (int j=0; j<getSize(); ++j)
            if (arr[i][j].get_player_type().equals(".")) return true;
        return false;    
}
    /**
     * 
     * @param type type of player for cell
     * @param current x - axis of cell
     * @param position y - axis of cell
     * @param value      for if four cells matching convert to lowercase or not.(value=1 convert lowercase
     *                   value=0 not convert to lowercase)   
     * @return true if one of players win the game.
     */
    private boolean determineTheWinWho(String type,int current,int position,int valuE){
    
    return  findTheConnectFour(current,type,valuE,position) ||
            findTheConnectFour(type,current,valuE,position) ||
            findTheConnectFour(valuE,position,type,current) ||
            findTheConnectFour(current,valuE,position,type);
}
    
    private boolean isCellAvaiable(int row){
        for (int i=getSize()-1; i>=0; --i)
            if (arr[i][row].get_player_type().equals(".")) return true;
        return false;
}/**
 * takes size and chooise(player-player or player-computer) from user.
 * initial the game.
 */
    public void playGame() {
    int size;
    String chooise,number;
    boolean flag=true;
    //Scanner input = new Scanner(System.in);
    number=JOptionPane.showInputDialog("Enter the size:");
    size=Integer.parseInt(number);
    while(flag) {
        chooise = JOptionPane.showInputDialog("Choose the P or C for P-P and P-C:");
        if (chooise.equalsIgnoreCase("P") || chooise.equalsIgnoreCase("C")) {
        
            gameType=chooise;
            flag=false;
        }
    }
    try
    {
        setSize(size);
    }
    catch(Exception e)
    {
     System.out.println(e.getMessage());
    }
}
/**
 * 
 * @param column
 * @param playerType
 * @return avaiable row according to column
 * if there is no avaiable cell in desired column,
 * function return -1.
 */
    private int getRowOfAvaiable(int column,String playerType){
    int row = -1;
    for (int i=getSize()-1; i>=0; --i)
        if (arr[i][column].get_player_type().equals(playerType)) {
            row=i;
            return row;
        }
    return row;
}
 /**
 * 
 * @param filename icon picture for Computer.
 * @param symbol   type of Computer("O")
 * @return true if Computer match four cells diagonal or vertical or horizontal.
 */
    private boolean moveOfComputer(String filename,String symbol){
    
    boolean flag=true;
    int move=0;
    int row;
    int randomNumber;
    Image image = null;
    try {
           image = ImageIO.read(getClass().getResource(filename));
    } 
    catch (IOException ex) {
            Logger.getLogger(ConnectFour.class.getName()).log(Level.SEVERE, null, ex);
    }
    //find four matching.
    for (int i = 0; i < getSize() && flag; ++i) {
        if (getRowOfAvaiable(i,".") != -1 ) {
            row=getRowOfAvaiable(i, ".");
            arr[row][i].set_PlayerType(symbol);
            if (determineTheWinWho(symbol,row,i,1))  {
                flag=false;
                move=i;
            }
            else {
                arr[row][i].set_PlayerType(".");
            }
        }

    }
    
    if (flag==false) return true;
    move=0;
    //prevent the four matching.
    for (int i = 0; i < getSize() && flag; ++i) {
        if (getRowOfAvaiable(i,".") != -1) {
            row=getRowOfAvaiable(i, ".");
            arr[row][i].set_PlayerType("X");
            if (determineTheWinWho("X",row,i,0))  {
                flag=false;
                arr[row][i].set_PlayerType(symbol);
                button[row][i].setIcon(new ImageIcon(image));
            }
            else {
                arr[row][i].set_PlayerType(".");
            }
        }

    }
    if (flag==false) return false;
    move=0;
    for (int i = 0; i < getSize() && flag; ++i) {
        if (getRowOfAvaiable(i,".") != -1 && isCellLegal(i+1,i) && isCellLegal(i+2,i) &&
		arr[i+1][i].get_player_type().equals(symbol) && arr[i+2][i].get_player_type().equals(symbol)) {
		    row = getRowOfAvaiable(i, ".");
                    arr[row][i].set_PlayerType(symbol);
		    button[row][i].setIcon(new ImageIcon(image));
		    flag=false;
	    }

        else if (getRowOfAvaiable(i,".") != -1 && isCellLegal(i+1,i)
                  && arr[i+1][i].get_player_type().equals(symbol)){
            row = getRowOfAvaiable(i,".");
            arr[row][i].set_PlayerType(symbol);
             button[row][i].setIcon(new ImageIcon(image));
            flag=false;
        }

    }

    
    if (flag==false) return false;
    move=0;
    // move random.
    do
    {
        Random rand = new Random();
        randomNumber=rand.nextInt(getSize());
        if (isCellAvaiable(randomNumber)) {
            row=getRowOfAvaiable(randomNumber,".");
            if(row != -1) {
                arr[row][randomNumber].set_PlayerType(symbol);
                button[row][randomNumber].setIcon(new ImageIcon(image));
                flag=false;
            }
        }
    }while(flag);
    return false;
}
}