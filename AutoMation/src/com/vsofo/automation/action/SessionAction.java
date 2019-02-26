package com.vsofo.automation.action;

import com.vsofo.automation.entity.UserItem;
import com.vsofo.automation.utils.SessionUtils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/8/8
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/8/8
 * @修改描述:
 */
public class SessionAction implements HttpSessionListener {
    private static SessionActionListener mSessionActionListener;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //System.out.println("session创建...");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        UserItem item = (UserItem) httpSessionEvent.getSession().getAttribute(SessionUtils.USER);
        //System.out.println("session失效了...");
        if (item != null) {
            try {
                mSessionActionListener.removeSessionId(item.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setListener(SessionActionListener sessionActionListener) {
        mSessionActionListener = sessionActionListener;
    }

    public interface SessionActionListener {
        void removeSessionId(int id) throws Exception;
    }
}
