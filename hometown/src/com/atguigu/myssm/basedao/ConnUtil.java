package com.atguigu.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
//    druid方式 暂时先不用
//
//    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
//    //private static ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
//    //private static ThreadLocal<Object> threadLocal3 = new ThreadLocal<>();
//
//
//    static Properties properties = new Properties();
//
//    static {
//
//        InputStream inStream = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//
//        try {
//            properties.load(inStream);
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private static Connection createConn() {
//
//        try {
//            DataSource source = DruidDataSourceFactory.createDataSource(properties);
//
//            return source.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
//
//    public static Connection getConn() {
//        Connection conn = threadLocal.get();
//        if (conn == null) {
//            conn = createConn();
//            threadLocal.set(conn);
//        }
//        return threadLocal.get();
//    }
//
//    public static void closeConn() throws SQLException {
//        Connection conn = threadLocal.get();
//        if (conn == null) {
//            return;
//        }
//        if (!conn.isClosed()) {
//            conn.close();
//            //threadLocal.set(null);
//            threadLocal.remove();
//        }
//    }


    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    //private static ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
    //private static ThreadLocal<Object> threadLocal3 = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://mysql.sqlpub.com:3306/hometown?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "xmut2615";
    public static final String PWD = "f424d4767147afdc" ;

    private static Connection createConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn==null){
            conn =createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get() ;
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn==null){
            return ;
        }
        if(!conn.isClosed()){
            conn.close();
            //threadLocal.set(null);
            threadLocal.remove();
        }
    }
}
