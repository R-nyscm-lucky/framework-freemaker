freemarker 导入模块使用规则：
    
    处理String替换规则：
      eg:
       String templateValue = "用户名：${userName}\n" +
                       "  密  码：${userPassword}\n" +
                       "  年  龄: ${age}\n";
               Map<String , Object> map = new HashMap<>();
               map.put("userName" , "张三" );
               map.put("userPassword" , "李四" );
               map.put("age" , 21 );
               return freemarkerService.parse(templateValue , map );
                 
    处理List替换规则：
      eg：
       String templateValue = "<#--Freemarker遍历list并按用户年龄降序排序-->\n" +
                      "按用户年龄降序排序：\n" +
                      "<#list userList?sort_by(\"age\")?reverse as user>\n" +
                      "  用户名：${user.userName}\n" +
                      "  密  码：${user.userPassword}\n" +
                      "  年  龄: ${user.age}\n" +
                      " \n" +
                      "</#list>";
      
              List<User> list = new ArrayList<>();
              list.add(new User("1" , "1" , 21));
              list.add(new User("2" , "2" , 22));
              list.add(new User("3" , "3" , 23));
              list.add(new User("4" , "4" , 24));
              Map<String , Object> map = new HashMap<>();
              map.put("userList" , list );
              return freemarkerService.parse(templateValue , map );
              
    处理Entity替换规则：
       eg：  
         String templateValue = "用户名：${user.userName}\n" +
                             "  密  码：${user.userPassword}\n" +
                             "  年  龄: ${user.age}\n";
                     Map<String , Object> map = new HashMap<>();
                     map.put("user" , new User("1" , "1" , 21) );
                     return freemarkerService.parse(templateValue , map );
                     
     freemarker 使用注意点：
        String替换规则：名字不能包括小数点 ".",小数点为实体替换规则存在.
                 
     
   
             
                 
             
     
     
    
   