package com.gjn.gamequery.sql;

/**
 * @author gjn
 * @time 2018/8/13 16:56
 */

public class JumpDBModel {
    private long matchId;
    private String json;

    public JumpDBModel() {
    }

    public JumpDBModel(long matchId, String json) {
        this.matchId = matchId;
        this.json = json;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
