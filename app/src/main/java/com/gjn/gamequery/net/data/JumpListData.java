package com.gjn.gamequery.net.data;

import java.util.List;

/**
 * JumpListData
 * Created by gjn
 * on 2018-08-05 23:09.
 */
public class JumpListData {
    private String Result;
    private java.util.List<ListBean> List;

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        private long MatchID;
        private int MatchType;
        private int HeroLevel;
        private int Result;
        private String MatchDate;
        private HeroBean Hero;

        public long getMatchID() {
            return MatchID;
        }

        public void setMatchID(long MatchID) {
            this.MatchID = MatchID;
        }

        public int getMatchType() {
            return MatchType;
        }

        public void setMatchType(int MatchType) {
            this.MatchType = MatchType;
        }

        public int getHeroLevel() {
            return HeroLevel;
        }

        public void setHeroLevel(int HeroLevel) {
            this.HeroLevel = HeroLevel;
        }

        public int getResult() {
            return Result;
        }

        public void setResult(int Result) {
            this.Result = Result;
        }

        public String getMatchDate() {
            return MatchDate;
        }

        public void setMatchDate(String MatchDate) {
            this.MatchDate = MatchDate;
        }

        public HeroBean getHero() {
            return Hero;
        }

        public void setHero(HeroBean Hero) {
            this.Hero = Hero;
        }

        public static class HeroBean {
            private int ID;
            private String Name;
            private String IconFile;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getIconFile() {
                return IconFile;
            }

            public void setIconFile(String IconFile) {
                this.IconFile = IconFile;
            }
        }
    }
}
