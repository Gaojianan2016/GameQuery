package com.gjn.gamequery.net.data;

import java.util.List;

/**
 * JumpRoleData
 * Created by gjn
 * on 2018-08-05 23:51.
 */
public class JumpRoleData {
    private String Result;
    private RoleBean Role;
    private List<RankBean> Rank;

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public RoleBean getRole() {
        return Role;
    }

    public void setRole(RoleBean Role) {
        this.Role = Role;
    }

    public List<RankBean> getRank() {
        return Rank;
    }

    public void setRank(List<RankBean> Rank) {
        this.Rank = Rank;
    }

    public static class RoleBean {
        private String RoleName;
        private long RoleID;
        private int RoleLevel;
        private int JumpValue;
        private int WinCount;
        private int MatchCount;
        private String UpdateTime;

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public long getRoleID() {
            return RoleID;
        }

        public void setRoleID(long RoleID) {
            this.RoleID = RoleID;
        }

        public int getRoleLevel() {
            return RoleLevel;
        }

        public void setRoleLevel(int RoleLevel) {
            this.RoleLevel = RoleLevel;
        }

        public int getJumpValue() {
            return JumpValue;
        }

        public void setJumpValue(int JumpValue) {
            this.JumpValue = JumpValue;
        }

        public int getWinCount() {
            return WinCount;
        }

        public void setWinCount(int WinCount) {
            this.WinCount = WinCount;
        }

        public int getMatchCount() {
            return MatchCount;
        }

        public void setMatchCount(int MatchCount) {
            this.MatchCount = MatchCount;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }
    }

    public static class RankBean {
        private int Type;
        private String RankName;
        private String ValueName;
        private long Rank;
        private String Value;
        private int RankChange;
        private long RankIndex;

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getRankName() {
            return RankName;
        }

        public void setRankName(String RankName) {
            this.RankName = RankName;
        }

        public String getValueName() {
            return ValueName;
        }

        public void setValueName(String ValueName) {
            this.ValueName = ValueName;
        }

        public long getRank() {
            return Rank;
        }

        public void setRank(long Rank) {
            this.Rank = Rank;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public int getRankChange() {
            return RankChange;
        }

        public void setRankChange(int RankChange) {
            this.RankChange = RankChange;
        }

        public long getRankIndex() {
            return RankIndex;
        }

        public void setRankIndex(long RankIndex) {
            this.RankIndex = RankIndex;
        }
    }
}
