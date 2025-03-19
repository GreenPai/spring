package kr.co.ch07.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.ch07.entity.shop.*;
import kr.co.ch07.repository.shop.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QCustomer qCustomer = QCustomer.customer;
    QProduct qProduct = QProduct.product;
    QOrder qOrder = QOrder.order;

    @Test
    public void test1(){
        List<Customer> customerList = customerRepository.selectAllCustomer();
        System.out.println(customerList);
    }

    @Test
    public void test2(){
        Customer customer = customerRepository.selectCustomer("a101");
        System.out.println(customer);
    }

    @Test
    public void test3(){
        List<Product> products = jpaQueryFactory.selectFrom(qProduct).fetch();
        System.out.println(products);
    }

    @Test
    public void test4(){
        List<Customer> customers = jpaQueryFactory
                                                .select(
                                                        Projections.fields(
                                                                Customer.class,
                                                                qCustomer.custid,
                                                                qCustomer.name,
                                                                qCustomer.age
                                                        )
                                                )
                                                .from(qCustomer)
                                                .fetch();
        System.out.println(customers);
    }

    @Test
    public void test5(){
        List<Customer> customer1 = jpaQueryFactory
                                .selectFrom(qCustomer)
                                .where(qCustomer.name.eq("김유신"))
                                .fetch();

        System.out.println(customer1);

    }

    @Test
    public void test6(){
        List<Customer> result1 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.gt(30)).fetch();
        List<Customer> result2 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.goe(30)).fetch();
        List<Customer> result3 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.lt(30)).fetch();
        List<Customer> result4 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.loe(30)).fetch();

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);

    }

    @Test
    public void test7(){

    }

    @Test
    public void test8(){

    }

    @Test
    public void test9(){

    }

    @Test
    public void test10(){

    }

    @Test
    public void test11(){

    }

    @Test
    public void test12(){

    }

    @Test
    public void test13(){

    }
}