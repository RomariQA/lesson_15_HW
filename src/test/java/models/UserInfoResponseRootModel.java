package models;

import lombok.Data;

@Data
public class UserInfoResponseRootModel {

        private UserInfoResponseDataModel data;
        private UserInfoResponseSupportModel support;

}
