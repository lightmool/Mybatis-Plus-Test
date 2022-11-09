package com.light.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName("t_user") //已经使用全局配置
public class User {
    @TableId(value = "uid",type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private int isdelete;
}
