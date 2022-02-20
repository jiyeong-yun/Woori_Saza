<template>
    <div>
        <!-- {{comment}} -->
        <div class="box mt-3 p-3" style="cursor:pointer" @click="moveToArticle" >
            <b-row>
                <b-col cols="8">
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" >{{comment.title}}</div>
                        <div style="font-size:0.9em">{{comment.content}}</div>
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:1em;font-weight:bold;">{{comment.createAt[0]}}.{{comment.createAt[1]}}.{{comment.createAt[2]}}</div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Comment',
    props:{
        comment : Object
    },
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        moveToArticle(){
            this.$router.push("/board/"+this.comment.articleId).catch(()=>{});
        },
    },
};
</script>

<style scoped>
.box{
    border-radius:1em;
    background-color: #F6F9FB;
    box-shadow: 0.1em 0.1em gray;
}
</style>