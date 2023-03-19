package com.bh.roommate.application.api.service.da.form;

import com.bh.roommate.application.api.model.Form;
import com.bh.roommate.application.api.service.da.OperationResponse;
import lombok.NonNull;

import java.util.List;

public interface FormService {

    OperationResponse<Form> delete(@NonNull Long id);

    OperationResponse<Form> create(@NonNull Form form);

    OperationResponse<Form> update(@NonNull Form form);

    OperationResponse<Form> getById(@NonNull Long id, @NonNull Long idUser);

    OperationResponse<List<Form>> getAll();

}
