package yhb.entity;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScoreResultBean {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}

