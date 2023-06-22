package com.concert.dao.impl;

import com.concert.dao.FiltersDao;
import com.concert.entities.Event;
import com.concert.entities.FilterData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@AllArgsConstructor
public class FiltersDaoImpl implements FiltersDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<Event> findByFilters(Pageable pageable, FilterData filterData)  {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);

        Predicate predicate = cb.conjunction();

        if (filterData.getMinPrice() != null && !filterData.getMinPrice().equals("")) {
            Predicate minPricePredicate = cb.greaterThanOrEqualTo(root.get("price"), filterData.getMinPrice());
            predicate = cb.and(predicate, minPricePredicate);
        }

        if (filterData.getMaxPrice() != null  && !filterData.getMaxPrice().equals("")) {
            Predicate maxPricePredicate = cb.lessThanOrEqualTo(root.get("price"), filterData.getMaxPrice());
            predicate = cb.and(predicate, maxPricePredicate);
        }

        if (filterData.getSelectedCity() != null  && !filterData.getSelectedCity().equals("")) {
            Predicate cityPredicate = cb.equal(root.get("location"), filterData.getSelectedCity());
            predicate = cb.and(predicate, cityPredicate);
        }

        if (filterData.getSelectedDate() != null && !filterData.getSelectedDate().equals("")) {
            Predicate datePredicate = null;

            switch (filterData.getSelectedDate()) {
                case "Сьогодні" -> {
                    LocalDate today = LocalDate.now();
                    datePredicate = cb.equal(root.get("date"), today);
                }
                case "Завтра" -> {
                    LocalDate tomorrow = LocalDate.now().plusDays(1);
                    datePredicate = cb.equal(root.get("date"), tomorrow);
                }
                case "Цього тижня" -> {
                    LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                    LocalDate sunday = monday.plusDays(6);
                    datePredicate = cb.between(root.get("date"), monday, sunday);
                }
                case "Цього місяця" -> {
                    LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
                    LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
                    datePredicate = cb.between(root.get("date"), firstDayOfMonth, lastDayOfMonth);
                }
            }

            if (datePredicate != null) {
                predicate = cb.and(predicate, datePredicate);
            }
        }

        query.where(predicate);

        List<Event> events = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long totalCount = entityManager.createQuery(query)
                .getResultList().size();

        return new PageImpl<>(events, pageable, totalCount);
    }
}
