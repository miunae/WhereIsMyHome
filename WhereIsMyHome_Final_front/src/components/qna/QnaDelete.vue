<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QNA목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";
const qnaboardStore = "qnaboardStore";

export default {
  name: "QnaDelete",
  methods: {
    ...mapActions(qnaboardStore, ["deleteQna"]),
    deleteCurrentQna(){
        this.deleteQna(this.$route.params.qnano);
        setTimeout(() => {
          this.$router.push({name:"qnalist"});
        }, 2000);
    }
  },
  created() {
    this.deleteCurrentQna();
  },
};
</script>

<style></style>
