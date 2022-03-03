import java.sql.*;


public class Main {
    //java -jar testDrill-1.0.1.jar 'select * from dfs.`/workspace/drill/apache-drill-1.20.0/sample-data/nation.parquet`' | grep rs

    public static void main(String[] args) throws Exception {

        Class.forName("org.apache.drill.jdbc.Driver");

        System.out.println("args[0]="+ args[0]);

        Connection connection = DriverManager.getConnection("jdbc:drill:drillbit=121.11.118.203:31010");
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(args[0]);

        ResultSetMetaData meta = rs.getMetaData();
        System.out.print("rs>\t");
        for (int i=1; i<=meta.getColumnCount(); ++i){
            String labelName = meta.getColumnName(i);
            System.out.print(labelName + "\t");
        }
        System.out.println("");
        while(rs.next()){
            System.out.print("rs>\t");
            for (int i=1; i<=meta.getColumnCount(); ++i){

                String labelName = meta.getColumnName(i);
                System.out.print(rs.getString(labelName));
                System.out.print("\t");
            }
            System.out.println("");
        }
        connection.close();
    }
}
