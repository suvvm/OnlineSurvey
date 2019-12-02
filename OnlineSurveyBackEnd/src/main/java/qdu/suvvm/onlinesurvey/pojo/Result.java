package qdu.suvvm.onlinesurvey.pojo;

/**
 * @ClassName: Result
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2019/12/2 18:29
 */
public class Result {
    private Integer uid;
    private Integer iid;
    private String ans;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Result{" +
                "uid=" + uid +
                ", iid=" + iid +
                ", ans='" + ans + '\'' +
                '}';
    }
}
