树的性质判断是树的数据结构比较基本的操作，一般考到都属于非常简单的题目，也就是第一道入门题，面试中最好不能有问题，力求一遍写对，不要给面试官任何挑刺机会。LeetCode中关于树的性质有以下题目：
Maximum Depth of Binary Tree
Minimum Depth of Binary Tree
Balanced Binary Tree
Same Tree
Symmetric Tree

首先说说关于求树的深度的题目，最简单的是求最大深度Maximum Depth of Binary Tree，一般都是用递归实现。思路很简单，只需要对走到空结点返回0，然后其他依次按层递增，取左右子树中大的深度即可。Minimum Depth of Binary Tree稍微复杂一点，主要是要注意因为是取左右子树小的深度，但是有一种情况是不计入深度的，就是比如左子树彻底为空时，这种情况我们不会认为深度就是0，因为左边并没有叶子，按照定义我们是要找叶子结点的最小深度。所以需要对于左右是否为空做一个额外的判断。
求树的深度属于简单的题目，所以如果递归实现比较快的话，面试官可能会问非递归怎么实现，如果有时间的话还是得练习一下哈，原理跟LeetCode总结 -- 树的遍历篇是一致的。

Balanced Binary Tree是求深度的一道扩展题目，基本原理还是求深度。不过需要增加的环节是判断他是不是平衡树，因为深度是我们必须维护的量，如果选用额外的布尔变量来维护是否为平衡树也可以。不过这里可以利用深度大于0的性质，可以将平衡的树返回正常的深度值，而不平衡的则返回-1来进行区分，这样相当于用一个变量维护了想要的两种性质，代码实现也比较简单。

Same Tree也是比较基础的题目，和树的遍历时一样的，只是对两棵树同时做相同的遍历，然后进行一一比较，如果出现不同则返回false即可。

Symmetric Tree会稍微绕一点，不过想清楚跟Same Tree还是差不多，第一个不同点是要根据左右子树比较，其实就是把左右子树当成Same Tree中的两个树即可。第二个不同点是在递归过程中对于结点的左右子树进行互换比较，也就是左跟右比，右跟左比。

这篇总结主要提到了LeetCode中求树的一些基本性质的题目，这类题目比较简单，属于最低门槛题目，所以要力求bug free地一遍完成哈。