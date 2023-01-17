<template>
  <b-container v-if="qnas && qnas.length != 0" class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QnA 게시판</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">글번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
    <qna-list-item v-for="(qna, index) in qnas" :key="index" :qna="qna" />
  </tbody>
</table>
  </b-container>
  
  
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>QNA 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import QnaListItem from "@/components/qna/item/QnaListItem.vue";
import { mapState, mapActions } from 'vuex'

const qnaboardStore = "qnaboardStore";

export default {
    name : "QnaList",
    methods: {
      ...mapActions(qnaboardStore, ["getQna"]),
      getAllList(){
        this.getQna();
      },
      moveWrite() {
      this.$router.push({ name: "qnawrite" });
    },
    },
    created(){
      this.getAllList();
      console.log("hi");
    },
    components:{
        QnaListItem,
    },
    computed:{
        ...mapState(qnaboardStore, ["qnas"]),
    },

}
</script>

<style>

</style>