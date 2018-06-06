package Object;

import java.util.List;

public class JSONBean {
    private boolean has_more;
    private long log_id;
    private int result_sum;
    private List<WineList> result;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getResult_sum() {
        return result_sum;
    }

    public void setResult_sum(int result_sum) {
        this.result_sum = result_sum;
    }

    public List<WineList> getResult() {
        return result;
    }

    public void setResult(List<WineList> result) {
        this.result = result;
    }
}
