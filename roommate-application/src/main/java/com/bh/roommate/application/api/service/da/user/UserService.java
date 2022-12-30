package com.bh.roommate.application.api.service.da.user;

import com.bh.roommate.application.api.model.User;
import com.bh.roommate.application.api.model.dto.UserDto;
import com.bh.roommate.application.api.service.da.OperationResponse;
import lombok.NonNull;

public interface UserService {

    OperationResponse<User> delete(@NonNull Long id);

    OperationResponse<User> create(@NonNull User user);

    OperationResponse<User> update(@NonNull User user);

    OperationResponse<User> getById(@NonNull Long id);

}
