package com.gjn.gamequery.net.data;

import java.util.List;

/**
 * JumpMatchData
 * Created by gjn
 * on 2018-08-05 23:11.
 */
public class JumpMatchData {
    private String Result;
    private MatchBean Match;

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public MatchBean getMatch() {
        return Match;
    }

    public void setMatch(MatchBean Match) {
        this.Match = Match;
    }

    public static class MatchBean {
        private long MatchID;
        private int MatchType;
        private int WinSideKill;
        private int LoseSideKill;
        private int UsedTime;
        private String MatchDate;
        private int FindTs;
        private int FindCount;
        private List<SideBean> WinSide;
        private List<SideBean> LoseSide;

        public long getMatchID() {
            return MatchID;
        }

        public void setMatchID(long matchID) {
            MatchID = matchID;
        }

        public int getMatchType() {
            return MatchType;
        }

        public void setMatchType(int MatchType) {
            this.MatchType = MatchType;
        }

        public int getWinSideKill() {
            return WinSideKill;
        }

        public void setWinSideKill(int WinSideKill) {
            this.WinSideKill = WinSideKill;
        }

        public int getLoseSideKill() {
            return LoseSideKill;
        }

        public void setLoseSideKill(int LoseSideKill) {
            this.LoseSideKill = LoseSideKill;
        }

        public int getUsedTime() {
            return UsedTime;
        }

        public void setUsedTime(int UsedTime) {
            this.UsedTime = UsedTime;
        }

        public String getMatchDate() {
            return MatchDate;
        }

        public void setMatchDate(String MatchDate) {
            this.MatchDate = MatchDate;
        }

        public int getFindTs() {
            return FindTs;
        }

        public void setFindTs(int FindTs) {
            this.FindTs = FindTs;
        }

        public int getFindCount() {
            return FindCount;
        }

        public void setFindCount(int FindCount) {
            this.FindCount = FindCount;
        }

        public List<SideBean> getWinSide() {
            return WinSide;
        }

        public void setWinSide(List<SideBean> WinSide) {
            this.WinSide = WinSide;
        }

        public List<SideBean> getLoseSide() {
            return LoseSide;
        }

        public void setLoseSide(List<SideBean> LoseSide) {
            this.LoseSide = LoseSide;
        }

        public static class SideBean {
            private String RoleName;
            private long RoleID;
            private int RoleLevel;
            private int HeroID;
            private int HeroLevel;
            private int Result;
            private int TeamResult;
            private int IsFirstWin;
            private int KillCount;
            private int DeathCount;
            private int AssistCount;
            private int TowerDestroy;
            private int KillUnitCount;
            private int TotalMoney;
            private int RewardMoney;
            private int RewardExp;
            private int JumpValue;
            private int WinCount;
            private int MatchCount;
            private int ELO;
            private int KDA;
            private HeroBean Hero;
            private List<Integer> SkillID;
            private List<Integer> EquipID;
            private List<SkillBean> Skill;
            private List<EquipBean> Equip;

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

            public int getHeroID() {
                return HeroID;
            }

            public void setHeroID(int HeroID) {
                this.HeroID = HeroID;
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

            public int getTeamResult() {
                return TeamResult;
            }

            public void setTeamResult(int TeamResult) {
                this.TeamResult = TeamResult;
            }

            public int getIsFirstWin() {
                return IsFirstWin;
            }

            public void setIsFirstWin(int IsFirstWin) {
                this.IsFirstWin = IsFirstWin;
            }

            public int getKillCount() {
                return KillCount;
            }

            public void setKillCount(int KillCount) {
                this.KillCount = KillCount;
            }

            public int getDeathCount() {
                return DeathCount;
            }

            public void setDeathCount(int DeathCount) {
                this.DeathCount = DeathCount;
            }

            public int getAssistCount() {
                return AssistCount;
            }

            public void setAssistCount(int AssistCount) {
                this.AssistCount = AssistCount;
            }

            public int getTowerDestroy() {
                return TowerDestroy;
            }

            public void setTowerDestroy(int TowerDestroy) {
                this.TowerDestroy = TowerDestroy;
            }

            public int getKillUnitCount() {
                return KillUnitCount;
            }

            public void setKillUnitCount(int KillUnitCount) {
                this.KillUnitCount = KillUnitCount;
            }

            public int getTotalMoney() {
                return TotalMoney;
            }

            public void setTotalMoney(int TotalMoney) {
                this.TotalMoney = TotalMoney;
            }

            public int getRewardMoney() {
                return RewardMoney;
            }

            public void setRewardMoney(int RewardMoney) {
                this.RewardMoney = RewardMoney;
            }

            public int getRewardExp() {
                return RewardExp;
            }

            public void setRewardExp(int RewardExp) {
                this.RewardExp = RewardExp;
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

            public int getELO() {
                return ELO;
            }

            public void setELO(int ELO) {
                this.ELO = ELO;
            }

            public int getKDA() {
                return KDA;
            }

            public void setKDA(int KDA) {
                this.KDA = KDA;
            }

            public HeroBean getHero() {
                return Hero;
            }

            public void setHero(HeroBean Hero) {
                this.Hero = Hero;
            }

            public List<Integer> getSkillID() {
                return SkillID;
            }

            public void setSkillID(List<Integer> SkillID) {
                this.SkillID = SkillID;
            }

            public List<Integer> getEquipID() {
                return EquipID;
            }

            public void setEquipID(List<Integer> EquipID) {
                this.EquipID = EquipID;
            }

            public List<SkillBean> getSkill() {
                return Skill;
            }

            public void setSkill(List<SkillBean> Skill) {
                this.Skill = Skill;
            }

            public List<EquipBean> getEquip() {
                return Equip;
            }

            public void setEquip(List<EquipBean> Equip) {
                this.Equip = Equip;
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

            public static class SkillBean {
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

            public static class EquipBean {
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
}
