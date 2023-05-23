package com.hspedu.spring.service;

import com.hspedu.spring.dao.MemberDAOImpl;

/**
 * @author 韩顺平
 * @version 1.0
 * Service类
 */
public class MemberServiceImpl {

    private MemberDAOImpl memberDAO;

    public MemberServiceImpl() {
        //System.out.println("MemberServiceImpl() 构造器被执行");
    }

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAOImpl memberDAO) {
        //System.out.println("setMemberDAO()...");
        this.memberDAO = memberDAO;
    }

    public void add() {
        System.out.println("MemberServiceImpl add() 被调用..");
        memberDAO.add();
    }
}
