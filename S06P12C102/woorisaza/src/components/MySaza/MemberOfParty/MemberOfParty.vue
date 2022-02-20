<template>
    <div>
        <!-- 내가 참가한 파티 목록 -->
        <div v-if="partyList.length == 0" style="text-align:center">
            아직 참가한 파티가 없습니다. 파티에 참가해보세요!
        </div>
        <div v-if="partyList.length > 0">
            <div v-for="(item, index) in partyList" :key="index">
                <Article :party="item"></Article>
            </div>
        </div>
    </div>
</template>

<script>
import Article from "@/components/MySaza/MemberOfParty/Article.vue";
import {axios_contact} from "@/common.js"

export default {
    name: 'Memberofparty',
    components:{
        Article,
    },
    data() {
        return {
            partyList:[],
            id : this.$route.params.id,
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "party/"+this.id,
        }).then(({data})=>{
            // console.log(data)
            for (let index = 0; index < data.length; index++) {
                if(!data[index].isBoss){
                    this.partyList.push(data[index])
                }
            }
        })
    },
    
    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>

</style>