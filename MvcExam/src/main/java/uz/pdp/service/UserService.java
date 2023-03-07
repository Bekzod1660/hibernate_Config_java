package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDao;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
}
