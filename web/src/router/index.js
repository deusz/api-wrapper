import Vue from 'vue'
import Router from 'vue-router'
import PreorderDetails from '@/components/PreorderDetails'
import PreordersList from '@/components/PreordersList'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'preorder-list',
            component: PreordersList
        },
        {
            path: '/preorders/:entityId',
            name: 'preorder-get',
            component: PreorderDetails
        }
    ]
})
