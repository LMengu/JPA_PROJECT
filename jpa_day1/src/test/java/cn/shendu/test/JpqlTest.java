package cn.shendu.test;

import cn.shendu.domain.Customer;
import cn.shendu.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpqlTest {
    /**
     * 查询全部
     *      jpql: from cn.shendu.domain. Customer
     *      sql:SELECT * FROM cst_customer
     */
    @Test
    public void testFindAll(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象啊

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 排序查询：倒序查询所有客户（根据id倒叙）
     *      sql:select * from cst_cutomer ORDER BY cust_id DESC
     *      JPQL:from Customer order by custId
     *
     *    进行jpql查询：
     *          1.创建query查询对象
     *          2.对参数进行赋值
     *          3.查询，并得到返回结果
     */
    @Test
    public void testOrders(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="from Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象啊

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql: Select COUNT(cust_id) From cst_cutomer
     *      jpql:select count(custId) from Customer
     */
    @Test
    public void testCount(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //根据jpql语句创建Query查询对象
        String jpql="select count(custId) from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象啊
        //对参数赋值
        //发送查询，并封装结果集
        /**
         * getTesultList: 直接将查询结果封装为List集合
         * getSingResult:得到唯一结果集
         */
        Object result = query.getSingleResult();
        System.out.println(result);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 分页查询
     *      sql: select * from cst_customer limit ?,?
     *      jpql：from Customer
     */
    @Test
    public void testPaged(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //根据jpql语句创建Query查询对象
        String jpql="from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象啊
        //对参数赋值
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        //发送查询，并封装结果集
        /**
         * getTesultList: 直接将查询结果封装为List集合
         * getSingResult:得到唯一结果集
         */
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 条件查询：
     *      案例： 查询客户名称以'深度编码'开头的客户
     *              sql: select * from cust_customer where cust_name LIKE ?
     *              jpql: from Customer where custName like ?
     *
     */
    @Test
    public void testCondition(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //根据jpql语句创建Query查询对象
        String jpql="from Customer where custName like ?";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象啊
        //对参数赋值
        //第一个参数，占位符的索引位置（从1开始），第二个参数：取值
        query.setParameter(1,"深度编码%");
        //发送查询，并封装结果集
        /**
         * getTesultList: 直接将查询结果封装为List集合
         * getSingResult:得到唯一结果集
         */
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}
