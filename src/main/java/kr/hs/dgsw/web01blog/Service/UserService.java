package kr.hs.dgsw.web01blog.Service;



import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;

import java.util.List;

public interface UserService {
    List<User> listAllUser();

    User viewUser();

    User addUser(User user);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);

    AttachmentProtocol getUserImageByID(Long id);

    User loginUser(User user);
}
