package kr.co.ch07.repository.shop.impl;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.ch07.entity.shop.Customer;

import kr.co.ch07.entity.shop.QCustomer;
import kr.co.ch07.repository.shop.custom.CustomerRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final JPAQueryFactory queryFactory;

    // Q도메인 클래스 선언
    QCustomer qCustomer = QCustomer.customer;

    @Override
    public List<Customer> selectAllCustomer() {

        List<Customer> customerList = queryFactory
                                        .select(qCustomer)
                .from(qCustomer).fetch();


        return customerList;
    }

    @Override
    public Customer selectCustomer(String custId) {

        return queryFactory
                .selectFrom(qCustomer)
                .where(qCustomer.custid.eq(custId))
                .fetchOne();
    }
}
