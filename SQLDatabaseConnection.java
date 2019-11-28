package newJava01;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class SQLDatabaseConnection {
	
	
	// 初始sql指令
	private static final String DEFAULT_QUERY =  "select employeeid,lastname,birthdate,title from employees";
	private static final String DEFAULT_RESULT = "MY_RESULT";
	private static String query = "";
	private static String result = "";
	private static ArrayList<String> allResult;
	private static String title = "";//
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
				
				allResult = new ArrayList<String>();
		
				//文字區塊
				final JTextArea queryArea = new JTextArea(DEFAULT_QUERY,3,100);
				queryArea.setWrapStyleWord(true);
				queryArea.setLineWrap(true);
				
				
				//顯示文字區塊
				final JTextArea resultArea = new JTextArea(DEFAULT_RESULT,8,100);
				resultArea.setWrapStyleWord(true);
				resultArea.setLineWrap(true);
				resultArea.setEditable(false);
				
				//上方區塊內的元件-卷軸
				JScrollPane scrollPane = new JScrollPane(queryArea
						, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
						, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				
				
				//提交按鈕
				JButton submitButton = new JButton("Submit Query");
				submitButton.addActionListener(
						new ActionListener() {
				            public void actionPerformed(ActionEvent e) {
				            	//清空資料
				            	allResult.clear();
				            	resultArea.setText("");
				            	result = "";
				            	query = "";
				            	query = queryArea.getText();
				            	//呼叫方法
				            	connectSQL(query);
				            	
				            	//組合回傳資料
				            	for (String i:allResult) {
									
									result = result + i ;
								}
				            	//顯示
				            	resultArea.setText(title+"\n"+result);
				            }
				        }	
				);
				
				
				//上方區塊
				Box boxNorth = Box.createHorizontalBox();
				boxNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
				boxNorth.add(scrollPane);
				boxNorth.add(submitButton);
				


				
				//下方區塊內的元件-卷軸
				JScrollPane scrollPane2 = new JScrollPane(resultArea
						, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
						, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

				//下方區塊
				Box boxSouth = Box.createHorizontalBox();
				boxSouth.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
				boxSouth.add(scrollPane2);
				

				//視窗,把上下方區塊加進去
				JFrame window = new JFrame("Displaying Query Results");

				window.add(boxNorth, BorderLayout.NORTH);
				window.add(boxSouth, BorderLayout.SOUTH);
				window.setSize(500, 270);
				window.setVisible(true);
	}
	
	
	//連接並讀取資料庫之方法
	public static void connectSQL(String s1) {
		ResultSet resultSet = null;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//輸入自己資料庫的URL
		    Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
		    								+ "databaseName=Northwind;user=sa;password=as;");
		             
		    DatabaseMetaData metadata = conn.getMetaData();   
		    System.out.println("Database Name: "+ metadata.getDatabaseProductName());
		    
		    
		    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    String selectSql = s1;
		    resultSet = statement.executeQuery(selectSql);
		    
		    //抓取輸入查詢欄位的次數
		    ResultSetMetaData rsmd = resultSet.getMetaData();
		    System.out.println(rsmd.getColumnCount());
		    
		    int count = rsmd.getColumnCount();
		    for (int i = 1; i <= count; i++) {
		    	title = title +"  ~  "+rsmd.getColumnName(i)+"  ~  ";
		    }
		    while(resultSet.next()) {
		    	
		    	for (int i = 1; i <= count; i++) {
			    	allResult.add("  [  "+resultSet.getString(i)+"  ]  ");
			    	if (i==rsmd.getColumnCount()) {
			    		allResult.add("\n");
			    	}
			    	
				}
		    	

		    }
		    
		    
		   
		    
		   

		    
		             
		} catch (Exception e) {
		   e.printStackTrace();
		   result = "指令錯誤:\n"+e.toString();
		} 
	}

}
