<template>
    <div>
        <!-- 나의 1:1 문의 -->
        <b-container>
          <div class="box mt-3 p-3" style="cursor:pointer" @click="toMyAnswer" >
              <b-row>
                  <b-col cols="8">
                      <div class="mt-2">
                          <div style="font-size:1.1em;font-weight:bold;margin-left:1em" >{{Qna.title}}</div>
                      </div>
                  </b-col>
                  <b-col cols="4">
                      <div style="text-align:right" class="mt-2">
                          <div style="font-size:0.75em;font-weight:bold;">{{Qna.category}}</div>
                          <div style="font-size:0.75em;font-weight:bold;">{{Qna.date[0]}}.{{Qna.date[1]}}.{{Qna.date[2]}}</div>
                      </div>
                  </b-col>
              </b-row>
          </div>
        </b-container>
        
    </div>
</template>

<script>

export default {
    name: 'Qna',
    components : {

    },
    props:["Qna"],
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
       toMyAnswer(){
           this.$router.push("/mypage/answer/"+this.Qna.id).catch(()=>{});;
       }
    },
};
</script>

<style scoped>
.btn-warning{
    width : 10em;
    background-color: #F1A501 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
}
.btn-secondary{
    width : 10em;
    background-color: #C4C4C4 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #C4C4C4;
    border-radius: 2em;
}
.btn-primary{
    width : 10em;
    background-color: #55A5EE ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #55A5EE;
    border-radius: 2em;
}
.box{
    border-radius:1em;
    background-color: #F6F9FB;
    box-shadow: 0.1em 0.1em gray;
}
</style>