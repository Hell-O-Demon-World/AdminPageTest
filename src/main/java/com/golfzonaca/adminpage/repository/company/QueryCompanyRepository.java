package com.golfzonaca.adminpage.repository.company;

import com.golfzonaca.adminpage.domain.Company;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.golfzonaca.adminpage.domain.QCompany.company;

@Transactional
@Repository
public class QueryCompanyRepository {

    private final JPAQueryFactory queryFactory;

    public QueryCompanyRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    List<Company> findCompanies(String keyWord) {
        return queryFactory
                .selectFrom(company)
                .where(containKeyWord(keyWord))
                .fetch();
    }

    private BooleanExpression containKeyWord(String keyWord) {

        if (StringUtils.hasText(keyWord)) {

            return company.Name.contains(keyWord);
        }
        return null;
    }
}
