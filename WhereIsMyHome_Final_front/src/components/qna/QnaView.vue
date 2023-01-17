<template>
  <b-container v-if="qna" class="bv-example-row" id="viewTemplate">
    <b-row>
      <b-col
        ><h3>{{ qna.subject }}</h3></b-col
      >
    </b-row>
    <!-- <b-row class="mb-2 mt-1">
      <b-col><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-col>
    </b-row> -->
    <b-row>
      <b-col>
        <b-alert show variant="secondary">고유 번호 : {{ qna.qnano }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary">제목 : {{ qna.subject }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">작성자 : {{ qna.userid }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning">내용 : {{ qna.content }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger">조회수 : {{ qna.hit }}</b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveQnaList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQna"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="moveDeleteQna"
          >글삭제</b-button
        >
      </b-col>
    </b-row>

    <b-container v-if="qna" class="bv-example-row" id="answerinput">
      <b-form @submit="onSubmit">
        <b-form-input
          id="answerno"
          v-model="answer.content"
          type="text"
          required
          placeholder="답변을 입력하세요..."
          ref="userid"
        >
        </b-form-input>
        <b-button type="submit" variant="primary" class="m-1"
          >답변작성</b-button
        >
      </b-form>
    </b-container>

    <b-container
      v-if="answers && answers.length != 0"
      class="bv-example-row mt-3"
    >
      <answer-list-item
        v-for="(answer, index) in answers"
        :key="index"
        :answer="answer"
      />
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show> 답변 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import AnswerListItem from "@/components/qna/item/AnswerListItem.vue";
const qnaboardStore = "qnaboardStore";
const memberStore = "memberStore";

export default {
  name: "QnaView",
  data() {
    return {
      answer: { qnaNo: "", userid: "" },
      // qna:mapState.qna,
    };
  },
  computed: {
    ...mapState(qnaboardStore, ["qna", "answers"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    console.log(this.qna.qnano);
    this.getAnswers(this.qna.qnano);
  },
  methods: {
    ...mapActions(qnaboardStore, ["deleteQna", "createAnswer", "getAnswers"]),
    moveQnaList() {
      this.$router.push({ name: "qnalist" });
    },
    moveDeleteQna() {
      // console.log(this.qna);
      this.$router.replace({
        name: "qnadelete",
        params: { qnano: this.qna.qnano },
      });
    },
    moveModifyQna() {
      this.$router.push({ name: "qnamodify" });
    },
    onSubmit(event) {
      this.answer.qnaNo = this.qna.qnano;
      this.answer.userid = this.userInfo.userid;
      event.preventDefault();
      this.createAnswer(this.answer);
      alert("댓글을 남겼습니다!!");
      this.answer = { qnaNo: "", userid: "" };
      this.getAnswers(this.qna.qnano);
    },
  },
  components: {
    AnswerListItem,
  },
};
</script>

<style>
/* #viewTemplate{
     display: flex;
    flex-direction: column;
    justify-content: space-between;
  } */
</style>