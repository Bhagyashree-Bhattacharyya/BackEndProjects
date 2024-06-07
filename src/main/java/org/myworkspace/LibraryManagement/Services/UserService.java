package org.myworkspace.LibraryManagement.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.myworkspace.LibraryManagement.DTOs.UserRequest;
import org.myworkspace.LibraryManagement.Entities.Filtering.Operator;
import org.myworkspace.LibraryManagement.Entities.Filtering.UserFilterType;
import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Entities.User.UserType;
import org.myworkspace.LibraryManagement.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User addConsumer(UserRequest userRequest){
        User user = userRequest.toUser();
        user.setUserType(UserType.CONSUMER);
        return userRepository.save(user);
    }

    public List filter(String filterBy, String operator, String value) {
        String[] filters = filterBy.split(",");
        String[] operators = operator.split(",");
        String[] values = value.split(",");
        StringBuilder query = new StringBuilder("select * from user where ");
        for (int i = 0; i < values.length; i++) {
            UserFilterType userFilterType = UserFilterType.valueOf(filters[i]);
            Operator operator_ = Operator.valueOf(operators[i]);
            String finalValue = values[i];
            query.append(userFilterType).append(operator_.getValue()).append("'")
                    .append(finalValue).append("'").append(" and ");
        }
        String finalQuery = query.substring(0, query.length() - 5);
        logger.info("query is :{}", finalQuery);

        Query query_ = em.createNativeQuery(finalQuery, User.class);
        return query_.getResultList();
    }

    public User getUserByPhoneNo(String userPhoneNo) {
        return userRepository.findByPhoneNo(userPhoneNo);
    }

    public User getConsumerByPhoneNo(String userPhoneNo) {
        return userRepository.findByPhoneNoAndUserType(userPhoneNo, UserType.CONSUMER);
    } // when only consumer can issue a book not admin
}
