<template>
   <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapActions, mapMutations } from "vuex";
import { deleteUser} from "@/api/member";
const memberStore = "memberStore";
export default {
    name: "UserDelete",
    created() {
        let param = this.$route.params.userid;
        deleteUser(
            param,
            ({data}) => {
                let msg = "탈퇴 처리시 문제가 발생했습니다.";
                if(data === "success") {
                    msg = "탈퇴가 완료되었습니다.";
                }
                alert(msg);
                // 현재 route를 /list로 변경.
                this.$router.push({ name: "login" });
            },
            (error) => {
              console.log(error);
            }
        );
        this.logout();
        
    },
    methods: {
    ...mapActions(memberStore, ["userLogout"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); 
      sessionStorage.removeItem("refresh-token"); 
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style>

</style>