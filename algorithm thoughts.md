# algorithm thoughts

Google某前员工Lucida在文章《白板编程访谈——Why，What，How》当中写道：”

程序员可以被分为两种:

1.先确认**前条件/不变式/终止条件/边界条件**，然后写出正确的代码

2.先编写代码，然后通过各种用例/测试/调试对程序进行调整，最后得到似乎正确的代码

保守估计前者的开发效率至少是后者的10倍，因为前者不需要浪费大量时间在 编码-调试-编码 这个极其耗时的循环上。通过白板编程，可以有效的判定出面试者属于前者还是后者，从而招进合适的人才，并把老油条或是嘴遁者排除在外。”

从硅谷名企Facebook、LinkedIn、Amazon、Google到国内的阿里、腾讯、百度、京东……各大IT公司都在用白板编程题目来考核程序员的开发效率和编程思路。


## 1.数据结构

### 1.1 数组
这篇总结主要介绍LeetCode中几道关于求kSum的题目， 主要要求是在数组中寻找k个数的和能否达到目标值。 LeetCode关于kSum的主要题目有：

Two Sum
3Sum
3Sum Closest
4Sum

首先从Two Sum开始， 这道题目提供了后面k>2的题目的基本思路， 也是最常考到的题目（亚马逊的面试对这道题算是情有独钟了）。

主要有两种思路，

一种是利用哈希表对元素的出现进行记录，然后进来新元素时看看能不能与已有元素配成target， 如果哈希表的访问是常量操作，这种算法复杂度是O(n)。

另一种思路则是先对数组进行排序， 然后利用夹逼的方法找出满足条件的pair， 算法的复杂度是O(nlogn)。

两种方法都比brute force的O(n^2)要优， 具体可以参见题目Two Sum的具体分析。

因为这道题模型简单， 但是可以考核到哈希表等基本数据结构和排序等基本算法， 所以是面试中的常客。

接下来是3Sum和3Sum Closest， 3Sum是使用Two Sum的第二种方法作为子操作， 然后循环每个元素， 寻找剩下的元素满足条件的pair即可。

 3Sum Closest和3Sum很类似， 只是要多维护一个最小的diff， 保存和target最近的值。
 算法的复杂度是O(n^2)， 这道题使用Two Sum的第一种方法并不合适， 因为当出现元素重复时用哈希表就不是很方便了。

最后是4Sum， 这道题的比较优的解法是用求解一般kSum的解法进行层层二分， 然后用Two Sum结合起来， 基本思路是这样， 实现细节还是比较复杂的， 即使是4Sum都比较复杂了， 所以面试中难度和实现细节也就到这了， kSum这种一般性的问题只需要知道思路就可以满足面试要求了哈。

### 1.2 字符串

### 1.3 链表
合并是一维数据结构中很常见的操作， 通常是排序， 分布式算法中的子操作。 这篇总结主要介绍LeetCode中关于合并的几个题目：

Merge Two Sorted Lists
Merge Sorted Array
Sort List
Merge k Sorted Lists

我们先来看看两个有序一维数据的合并， 这里主要是要介绍链表的合并操作， 不过因为一维数组的合并也比较简单， 而且与链表有比较性， 就顺便在这里列举一下。 Merge Two Sorted Lists就是要求合并两个有序链表， 一般来说合并的思路就是以一个为主参考， 然后逐项比较， 如果较小元素在参考链表中， 则继续前进， 否则把结点插入参考链表中， 前进另一个链表， 最后如果另一个链表还没到头就直接接过来就可以了， 思路和实现都比较简单， 最好力求一遍过哈。 Merge Sorted Array也是一样的思路， 只是数据结构换成了一维数组， 所以插入操作比链表要麻烦一些， 这里相当于从尾部开始， 然后数字一个个按照位置填入， 插入操作在这里就不明显了。 可以看出虽然思路近似， 但是数据结构不同， 因为操作不同， 实现细节还是比较不一样的， 都得熟练掌握哈。
有了上面合并两个链表（或者数组）我们就可以进行归并排序了， 也就是Sort List这道题目。 对于链表， 用Merge Two Sorted Lists作为合并的子操作， 然后用归并排序的递归进行分割合并就可以了， 这里就不说排序的具体细节了， 会有专门的排序总结篇哈。

最后我们来说说最重要的也是最实用的一个题目Merge k Sorted Lists。
为什么说他实用是因为现在分布式系统很多， 而且也很强调MapReduce或者Hadoop的技术， 这个题目就是分布式计算的一个常见操作。
比如说来自不同client的有序数据要在central server上面进行合并。 这个问题有两种做法：
第一种就是利用上面的Merge Two Sorted Lists对k个链表先进行两两合并， 然后再上一层继续两两合并， 直到合成一个链表。
根据Merge k Sorted Lists中的分析， 时间复杂度是O（nklogk）, 空间复杂度是O（logk）。

上面这种做法是利用分治然后进行合并的方法， 接下来这个方法用到了堆的数据结构。
思路是维护一个大小为k的堆， 每次取堆顶的最小元素放到结果中，然后读取该元素对应的链表的下一个元素放入堆中。 因为每个链表是有序的， 每次又是去当前k个元素中最小的， 所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。 这个算法每个元素要读取一次，即k*n次，然后每次读取元素要把新元素插入堆中要O（logk）的复杂度，所以复杂度是O(nklogk)， 跟第一种方法是一样的。
两种方法不同的数据结构和类型， 都非常具有代表性， 个人觉得都很有意思哈。

合并算是链表中比较典型的操作， 也能够连续地问出比较连贯的一些题目， 扩展性非常好， 既可以考察基本数据结构的操作， 又可以看看对于算法和更多数据结构的理解， 是一个不错的面试话题。

### 1.4 队列
### 1.5 栈
### 1.6 二叉树

#### 1.6.1 树的性质

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

#### 1.6.1 树的构造

这篇总结主要介绍树中比较常见的一类题型--树的构造。其实本质还是用递归的手法来实现，但是这类题目有一个特点，就是它是构建一棵树，而不是给定一棵树，然后进行遍历，所以实现起来思路上有点逆向，还是要练习一下。LeetCode中关于树的构造的题目有以下几道：

Convert Sorted Array to Binary Search Tree
Convert Sorted List to Binary Search Tree
Construct Binary Tree from Preorder and Inorder Traversal
Construct Binary Tree from Inorder and Postorder Traversal

先来看看最简单的Convert Sorted Array to Binary Search Tree，数组本身是有序的，那么我们知道每次只要取中点作为根，然后递归构建对应的左右子树就可以了，递归的写法跟常规稍有不同，就是要把根root先new出来，然后它的左节点接到递归左边部分的返回值，右节点接到递归右边部分的返回值，最后将root返回回去。这个模板在树的构造中非常有用，其他几道题也都是按照这个来实现。

接下来是Convert Sorted List to Binary Search Tree，这个跟Convert Sorted Array to Binary Search Tree比较近似，区别是元素存储的数据结构换成了链表，不过引入了一个重要的问题，就是链表的访问不是随机存取的，也就是不是O(1)的，如果每次去获取中点，然后进行左右递归的话，我们知道得到中点是O(n/2)=O(n)的，如此递推式是T(n) = 2T(n/2)+n/2，复杂度是O(nlogn)，并不是线性的，所以这里我们就得利用到树的中序遍历了，按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。如此就能按照链表的访问顺序来构造，不会因此而增加找中间结点的复杂度。

最后是Construct Binary Tree from Preorder and Inorder Traversal和Construct Binary Tree from Inorder and Postorder Traversal，这个方法还是跟上面的题目一样来构造，主要问题是如何将节点劈成左右两部分进行递归，Construct Binary Tree from Preorder and Inorder Traversal就是利用前序遍历跟一定在第一个，而中序遍历又可以根据根来把元素劈成两块，类似的Construct Binary Tree from Inorder and Postorder Traversal是根据后序遍历最后一个是根的特点，然后利用中序遍历劈块，原理是一样的，最后的实现大家可以参考一下代码。

这篇总结主要介绍了LeetCode中四个树的构造的题目，比较统一的思路就是在递归中创建根节点，然后找到将元素劈成左右子树的方法，递归得到左右根节点接上创建的根然后返回。方法还是比较具有模板型的，不熟悉的朋友可以练习一下哈。

#### 1.6.1 树的求和

树的求和属于树的题目中比较常见的，因为可以有几种变体，灵活度比较高，也可以考察到对于树的数据结构和递归的理解。一般来说这些题目就不用考虑非递归的解法了（虽然其实道理是跟LeetCode总结 -- 树的遍历篇一样的，只要掌握了应该没问题哈）。 LeetCode中关于树的求和有以下题目：

Path Sum
Path Sum II
Sum Root to Leaf Numbers
Binary Tree Maximum Path Sum

我们先来看看最常见的题目Path Sum。这道题是判断是否存在从根到叶子的路径和跟给定sum相同。树的题目基本都是用递归来解决，主要考虑两个问题：

1）如何把问题分治成子问题给左子树和右子树。这里就是看看左子树和右子树有没有存在和是sum减去当前结点值得路径，只要有一个存在，那么当前结点就存在路径。

2）考虑结束条件是什么。这里的结束条件一个是如果当前节点是空的，则返回false。另一个如果是叶子，那么如果剩余的sum等于当前叶子的值，则找到满足条件的路径，返回true。
想清楚上面两个问题，那么实现起来就是一次树的遍历，按照刚才的分析用参数或者返回值传递需要维护的值，然后按照递归条件和结束条件进行返回即可。算法的时间复
杂度是一次遍历O(n)，空间复杂度是栈的大小O(logn)。

对于Path Sum II，其实思路和Path Sum是完全一样的，只是需要输出所有路径，所以需要数据结构来维护路径，添加两个参数，一个用来维护走到当前结点的路径，一个用来保存满足条件的所有路径，思路上递归条件和结束条件是完全一致的，空间上这里会依赖于结果的数量了。

Sum Root to Leaf Numbers这道题多了两个变化，一个是每一个结点相当于位上的值，而不是本身有权重，不过其实没有太大变化，每一层乘以10加上自己的值就可以了。另一个变化就是要把所有路径累加起来，这个其实就是递归条件要进行调整，Path Sum中是判断左右子树有一个找到满足要求的路径即可，而这里则是把左右子树的结果相加返回作为当前节点的累加结果即可。

变化比较大而且有点难度的是Binary Tree Maximum Path Sum，这道题目的路径要求不再是从根到叶子的路径，这个题目是把树完全看成一个无向图，然后寻找其中的路径。想起来就觉得比上面那种麻烦许多，不过仔细考虑会发现还是有章可循的，找到一个根节点最大路径，无非就是找到左子树最大路径，加上自己的值，再加上右子树的最大路径（这里左右子树的路径有可能不取，如果小于0的话）。我们要做的事情就是对于每个结点都做一次上面说的这个累加。而左子树最大路径和右子树最大路径跟Path Sum II思路是比较类似的，虽然不一定要到叶子节点，不过标准也很简单，有大于0的就取，如果走下去路径和小于0那么就不取。从分治的角度来看，左右子树的最大路径就是取自己的值加上Max(0，左子树最大路径，右子树最大路径)。这么一想也就不用考虑那么多细节了。而通过当前节点的最长路径则是自己的值+Max(0，左子树最大路径)+Max(0，右子树最大路径)。所以整个算法就是维护这两个量，一个是自己加上左或者右子树最大路径作为它的父节点考虑的中间量，另一个就是自己加上左再加上右作为自己最大路径。具体的实现可以参见Binary Tree Maximum Path Sum。

这篇总结主要讲了LeetCode中关于树的求和的题目。总体来说，求和路径有以下三种：（1）根到叶子结点的路径；（2）父结点沿着子结点往下的路径；（3）任意结点到任意结点（也就是看成无向图）。这几种路径方式在面试中经常灵活变化，不同的路径方式处理题目的方法也会略有不同，不过最复杂也就是Binary Tree Maximum Path Sum这种路径方式，只要考虑清楚仍然是一次递归遍历的问题哈。

#### 1.6.1 树的遍历

遍历树的数据结构中最常见的操作， 可以说大部分关于树的题目都是围绕遍历进行变体来解决的。 一般来说面试中遇到树的题目是用递归来解决的， 不过如果直接考察遍历， 那么一般递归的解法就过于简单了， 面试官一般还会问更多问题， 比如非递归实现， 或者空间复杂度分析以及能否优化等等。 树的遍历题目在LeetCode中有以下几个：

Binary Tree Inorder Traversal
Binary Tree Preorder Traversal
Binary Tree Postorder Traversal
Binary Tree Level Order Traversal
Binary Tree Level Order Traversal II
Binary Tree Zigzag Level Order Traversal

树的遍历基本上分成两种类型， 下面分别介绍：

第一种是以图的深度优先搜索为原型的遍历， 可以是中序， 先序和后序三种方式， 不过结点遍历的方式是相同的， 只是访问的时间点不同而已， 对应于Binary Tree Inorder Traversal， Binary Tree Preorder Traversal和Binary Tree Postorder Traversal这三道题目。
在这种类型中， 递归的实现方式是非常简单的， 只需要递归左右结点， 直到结点为空作为结束条件就可以， 哪种序就取决于你访问结点的时间。
不过一般这不能满足面试官的要求， 可能会接着问能不能用非递归实现一下， 这个说起来比较简单， 其实就是用一个栈手动模拟递归的过程， Binary Tree Inorder Traversal和Binary Tree Preorder Traversal比较简单， 用一个栈来保存前驱的分支结点（相当于图的深度搜索的栈）， 然后用一个结点来记录当前结点就可以了。 而Binary Tree Postorder Traversal则比较复杂一些， 保存栈和结点之后还得根据情况来判断当前应该走的方向（往左， 往右或者回溯）。 这里就不列举代码细节， 有兴趣的朋友可以看看具体题目的分析， 会更详细一些。
有时候非递归还是不能满足面试官， 还会问一问， 上面的做法时间和空间复杂度是多少。 我们知道， 正常遍历时间复杂度是O(n), 而空间复杂度是则是递归栈（或者自己维护的栈）的大小， 也就是O(logn)。 好了， 他会问能不能够在常量空间内解决树的遍历问题呢？ 确实还真可以， 这里就要介绍Morris Traversal的方法。 Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。 这样就节省了需要用栈来记录前驱或者后继结点的额外空间， 所以可以达到O（1）的空间复杂度。 不过这种方法有一个问题就是会暂时性的改动树的结构， 这在程序设计中并不是很好的习惯， 这些在面试中都可以和面试官讨论， 一般来说问到这里不会需要进行Morris遍历方法的代码实现了， 只需要知道这种方法和他的主要优劣势就可以了， 有兴趣知道实现的朋友可以看看具体题目的实现哈。

另一种是以图的广度优先搜索为原型的， 在树中称为层序遍历， LeetCode中有三种自顶向下层序， 自底向上层序和锯齿层序遍历， 对应于Binary Tree Level Order Traversal， Binary Tree Level Order Traversal II和Binary Tree Zigzag Level Order Traversal。
Binary Tree Level Order Traversal其实比较简单， 代码基本就是图的广度优先搜索， 思路就是维护一个队列存储上一层的结点， 逐层访问。 而Binary Tree Level Order Traversal II则要从最后一层倒序访问上来， 这个我没有想到太好的方法， 现在的实现就是把Binary Tree Level Order Traversal得到的层放入数据结构然后reverse过来， 确实没有太大的考核意义。 至于Binary Tree Zigzag Level Order Traversal因为每一层访问顺序有所改变， 而且是每次都反转顺序， 这让我们想到栈的数据结构， 所以这里不用队列对于上层结点进行， 而改用栈来保存， 就可以满足每层反转访问顺序的要求了。

树的遍历是一个老生常谈的题目， 不过仔细研究还是有一些考点的， 对于考查对数据结构和算法的理解还是不错的， 所以简单的东西也得重视哈。

#### 1.6.1 二叉查找树

这篇总结主要介绍一个比较常见的数据结构--二叉查找树。二叉查找树既是一颗树，又带有特别的有序性质，所以考察的方式比较多而且灵活，属于面试题目中的常客。LeetCode中关于二叉查找树的题目有以下几道：

Validate Binary Search Tree
Recover Binary Search Tree
Unique Binary Search Trees
Unique Binary Search Trees II
Convert Sorted Array to Binary Search Tree
Convert Sorted List to Binary Search Tree

先来看看最基本的Validate Binary Search Tree，就是判断一个树是不是二叉查找树。比较简单而且明了的方法就是利用二叉查找树的中序遍历有序的性质，只要对树进行一次中序遍历，而其中的结点都满足有序即可，实现上就是维护一个前驱结点，每次判断前驱结点比当前结点要小。另一种方法是根据二叉查找树的定义来实现，保证结点满足它的左子树的每个结点比当前结点值小，右子树的每个结点比当前结点值大，实现上就是对于每个结点保存左右界，然后进行递归判断左右界不会违背即可。

Recover Binary Search Tree这道题目还是利用二叉查找树的主要性质，就是中序遍历是有序。那么如果其中有元素被调换了，意味着中序遍历中必然出现违背有序的情况。主要考虑到就是出现违背的次数问题。这里有两种情况：
（1）如果是中序遍历相邻的两个元素被调换了，很容易想到就只需会出现一次违反情况，只需要把这个两个节点记录下来最后调换值就可以；
（2）如果是不相邻的两个元素被调换了，会发生两次逆序的情况，那么这时候需要调换的元素应该是第一次逆序前面的元素，和第二次逆序后面的元素。

Unique Binary Search Trees这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。从处理子问题的角度来看，选取一个结点为根，就把结点切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以所有结点为根的可行结果累加起来。这其实是一个卡特兰数的模型，所以按照公式进行实现就可以。而Unique Binary Search Trees II则不能用卡特兰数，因为要求出所有结果，所以还是得走递归遍历的过程，然后把生成树来的树接上。

Convert Sorted Array to Binary Search Tree和Convert Sorted List to Binary Search Tree则是属于二叉查找树的构造问题，针对两种不同数据结构数组和链表进行构造。其实方法都是一样，就是递归对窗口进行圈限，然后用中间的结点作为当前根，再递归生成左右子树。链表的构造要稍微绕一些，因为要通过中序遍历走到第一个结点，然后递进链表。

这篇总结主要介绍LeetCode中关于二叉查找树的题目，二叉查找树因为是基本数据结构加上有可利用的有序性质，还是在面试中相当常见的，对于性质理解要深刻，实现要熟练哈。

### 1.7 堆
### 1.8 图

图的算法跟树一样是准备面试中必不可少的一块，不过图的方法很容易概括，面试中考核的无非就是两种搜索算法：深度优先搜索和广度优先搜索。LeetCode中关于图的问题有以下几个：

Clone Graph
Word Ladder
Word Ladder II
Longest Consecutive Sequence
Word Search
Surrounded Regions

先来看看最基础的Clone Graph，很简单就是要复制一个图，常见的两种搜索算法（深度和广度）都可以用，具体细节就不在这里解释了，不熟悉的朋友可以看看相关资料。建议大家还是两种都要练一练，因为在解决具体问题中这两种方法还是很常用的。

接下来的这些题都是基于图算法的应用，Word Ladder和Word Ladder II是比较典型的，看起来好像是字符串操作的题目，实际上这里得转换成图的角度来考虑，因为字符集比较小的缘故（26个小写字母），也就是说对于一个单词来说，改变其中一个字符可以有25条边（除去他自己），所以总共有（25*单词的长度L）条边。找到是否有满足一个单词转成另一个单词就是在这个图中找到一条路径。所以我们可以把问题转换成图用广度优先搜索来解决，找到即可停止。

Word Ladder是广度优先搜索的应用，而Longest Consecutive Sequence则是深度优先搜索的应用。题目要求是找出最长的连续整数串，如果把数字看成结点，与它相邻的整数连有边，那么找到最长的连续串就是在这个图中找最长路径。因为是最长路径，这里用深度优先搜索是比较适合的。

Word Search也是一道深度优先搜索的题目，是把上下左右相邻的结点看成有边联结，然后进行深度搜索就可以了，小细节是这里从每个点出发字符就可以重用，所以要重置一下访问结点。

Surrounded Regions要用一个图形学中很常用的填充算法：Flood fill 算法，其实本质还是一个深度优先搜索，跟Word Search一样是把相邻的上下左右看成连边，然后进行搜索填充。

图的问题其实本质都是两种搜索算法，难点主要在于对于具体问题如何想到转换成图的问题，然后用这两种搜索来解决，这也是算法中的一个分支，面试中也是常客哈。

## 2.二分查找
针对已排序的数组
从中间值开始查找，
若中间值大于目标值，则 low = mid + 1
若中间值小于目标值，则 high = mid
循环直到 low = high

二分查找算法虽然简单，但面试中也比较常见，经常用来在有序的数列查找某个特定的位置。在LeetCode用到此算法的主要题目有：
Search Insert Position
Search for a Range
Sqrt(x)
Search a 2D Matrix
Search in Rotated Sorted Array
Search in Rotated Sorted Array II

这类题目基本可以分为如下四种题型：
1. Search Insert Position和Search for a Range是考察二分查找的基本用法。基本思路是每次取中间，如果等于目标即返回，否则根据大小关系切去一半，因此时间复杂度是O(logn)，空间复杂度O(1)。以Search Insert Position为例，其关键代码写法如下：
    ```
    int l = 0;
    int r = A.length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(A[mid]==target)
            return mid;
        if(A[mid]<target)
            l = mid+1;
        else
            r = mid-1;
    }
    return l;
    ```
这样当循环停下来时，如果不是正好找到target，l指向的元素恰好大于target，r指向的元素恰好小于target，这里l和r可能越界，
不过如果越界就说明大于（小于）target并且是最大（最小）。Search for a Range这道题能更好的解释这一点。其思路是先用二分查找找到其中一个target，
然后再往左右找到target的边缘。我们主要看找边缘（往后找）的代码：

    ```
    int newL = m;
    int newR = A.length-1;
    while(newL<=newR)
    {
        int newM=(newL+newR)/2;
        if(A[newM]==target)
        {
            newL = newM+1;
        }
        else
        {
            newR = newM-1;
        }
    }
    res[1]=newR;
    ```

我们的目标是在后面找到target的右边界，因为左边界已经等于target，所以判断条件是相等则向右看，大于则向左看，根据上面说的，循环停下来时，l指向的元素应该恰好大于target，r指向的元素应该等于target，所以此时的r正是我们想要的。向前找边缘也同理。

2. Sqrt(x)是数值处理的题目，但同时也可以用二分查找的思想来解决。因为我们知道结果的范围，取定左界和右界，然后每次砍掉不满足条件的一半，直到左界和右界相遇。算法的时间复杂度是O(logx)，空间复杂度是O(1)。这里同样是考察二分查找的基本用法。代码如下：
```
public int sqrt(int x) {
    if(x<0) return -1;
    if(x==0) return 0;
    int l=1;
    int r=x/2+1;
    while(l<=r)
    {
        int m = (l+r)/2;
        if(m<=x/m && x/(m+1)<m+1)
            return m;
        if(x/m<m)
        {
            r = m-1;
        }
        else
        {
            l = m+1;
        }
    }
    return 0;
}
```
这里要注意，这里判断相等的条件不是简单的 m == x/m, 而是 m<=x/m && x/(m+1)<m+1, 这是因为输出是整型，sqrt(14)=3 但 3 != 14/3. 所以我们需要一个范围框住结果。另外根据二分查找算法的特性，如果不能正好m==x/m停下，那么r指向的数字将正好是结果取整的值。所以我们也可以这样写：
```
public int sqrt(int x) {
    if(x<0) return -1;
    if(x==0) return 0;
    int l=1;
    int r=x/2+1;
    while(l<=r)
    {
        int m = (l+r)/2;
        if(m==x/m )
            return m;
        if(x/m<m)
        {
            r = m-1;
        }
        else
        {
            l = m+1;
        }
    }
    return r;
}
```
3. Search a 2D Matrix是二分查找算法的多维应用，通过观察不难发现，输入的矩阵行内有序并且行间有序，所以查找只需要先按行查找，定位出在哪一行之后再进行列查找即可，两次二分查找，时间复杂度是O(logm+logn)，空间上只需两个辅助变量，因而是O(1)，这里不再赘述。

4. Search in Rotated Sorted Array和Search in Rotated Sorted Array II算是二分查找算法的一个变体。
在Search in Rotated Sorted Array中，乍一看感觉数组已经不是有序的了，也就无法用二分查找算法，但仔细分析一下会发现，
因为只rotate了一次，如果二分一下，总有一半是有序的，而且和另一半无区间重叠，我们只需要检查有序的一半的前后两个元素就可以确定target可能在哪一半。
具体来说，假设数组是A，每次左边缘为l，右边缘为r，还有中间位置是m。在每次迭代中，分三种情况：
（1）如果target==A[m]，那么m就是我们要的结果，直接返回；
（2）如果A[m]<A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，
否则就target在另一半，即把右边缘移到m-1。
（3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)。
Search in Rotated Sorted Array II中array有重复元素，按照刚刚的思路，二分之后虽然一半是有序的，但我们会遇到中间和边缘相等的情况，
我们就丢失了哪边有序的信息，因为哪边都有可能是有序的结果。假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，
或者{3,1,2,3,3,3,3}，这样的我们判断左边缘和中心的时候都是3，如果我们要寻找1或者2，我们并不知道应该跳向哪一半。
解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能。
所以最坏情况（比如全部都是一个元素，或者只有一个元素不同于其他元素，而他就在最后一个）就会出现每次移动一步，总共是n步，算法的时间复杂度变成O(n)。

总体来说，二分查找算法理解起来并不算难，但在实际面试的过程中可能会出现各种变体，如何灵活的运用才是制胜的关键。我们要抓住“有序”的特点，一旦发现输入有“有序”的特点，我们就可以考虑是否可以运用二分查找算法来解决该问题。

## 3.双指针

### 一. 首尾指针
两个指针一般是在有序数组中使用，一个放首，一个放尾，同时向中间遍历，直到两个指针相交，完成遍历，时间复杂度也是O(n)。

用法

一般会有两个指针front,tail。分别指向开始和结束位置。
  front = 0;
  tail = A.length()-1

一般循环结束条件采用的是判断两指针是否相遇
  while(fron < tail)
  {
  ……
  }

对于in place交换的问题，循环结束条件一般就是其中一个指针遍历完成。
使用范围
一般双指针在有序数组中使用的特别多。（部分情况下，未排序数组也有应用） 一般用来解决下列问题（陆续补充中）：
1. 两数求和
一般这种问题是问，寻找两个数的和为一个特定的值（比如后面的N SUM问题），这时候，如果数组有序，我们采用两个指针，分别从前和后往中间遍历，
front移动和增大，tail移动和减小，通过特定的判断，可以求出特定的和。
时间复杂度为O(n),如果用双重循环则要O(n^2)。
2. in place交换
数组的in place(就地)交换一般得用双指针，不然数组中添加或删除一个元素，需要移动大量元素。

这时候，一般是一个指针遍历，一个指针去找可以用来交换的元素。

### 二. 快慢指针


１概念
双指针：快慢指针。
快指针在每一步走的步长要比慢指针一步走的步长要多。快指针通常的步速是慢指针的2倍。

在循环中的指针移动通常为：
faster = faster.next.next; slower = slower.next;

2 应用
2.1. 用来判断链表是否有环以及寻找环入口
Linked List Cycle
Linked List Cycle II
是否有环：快慢指针思想，注意循环条件：(fast != null) && (fast.next != null)

寻找环的入口：快慢指针相遇的时候，distance(fast指针) = 2 * distance(slow指针)，可以推导出，只要把fast重新指向头结点，两个指针以一样的速度走，相遇的时候，便是环的入口。

2.2.数组寻找范围
Summary Ranges
范围的寻找，用2个指针:start ，end来记录范围。注意循环条件和判断条件：(end + 1 < len) && (nums[end + 1] == nums[end] + 1)

2.3.链表或者数组中移除重复的元素
Remove Duplicates from Sorted List I
Remove Duplicates from Sorted List II
Sorted List I用两个指针一前一后指向链表。维护两个指针:

tail 一个指向当前不重复的最后一个元素，
pCur 一个进行依次扫描，遇到不重复的则更新第一个指针，继续扫描，否则就把前面指针指向当前元素的下一个（即把当前元素从链表中删除）。
Sorted List II 维护两个指针：

prev前驱指针指向上一个不重复的元素
pCur遍历指针
思路类似Sorted List I，细节更多。
寻找不重复的元素 while循环条件pCur.next != null && prev.next.val == pCur.next.val
Array数组中的解题思想一样：

index指向上当前不重复的最后一个元素
i遍历数组
2.4. 用来找中点或中位数
2.5. 倒数第n个
题目中含有：倒数第n个，那么设置快指针步长为n，然后快慢指针同时以同一速度走，用慢指针寻找倒数第n个

2.6. 拆分链表
Partition List
给定一个x的值，小于x都放在大于等于x的前面，并且不改变链表之间node原始的相对位置。example中 4->3->5都是大于等3的数，这保持了他们原来的相对位置。

使用链表最常用的双指针：

一个指向当前小于x的最后一个元素
一个进行往前扫描。如果元素大于x，那么继续前进，否则，要把元素移到前面，并更新第一个指针。
Reorder List
思路：
1.利用快慢两个指针将链表一分为二；
2.针对第二个子链表求倒序；
3.利用merge思想将两个子链表合并。

3 相关题目
Summary Ranges
Linked List Cycle
Linked List Cycle II
Remove Duplicates from Sorted List I
Remove Duplicates from Sorted List II
Remove Duplicates from Sorted Array I
Remove Duplicates from Sorted Array II
Partition List
Intersection of Two Linked Lists
Remove Nth Node From End of List
Reorder List
Delete Node in a Linked List
4 注意
通常需要特别留意链表长度的奇偶性
如果快指针步速为慢指针步速2倍，循环条件为：faster.next!=null && faster.next.next!=null
当自行设置快指针步长时， 要考虑步长值等于链表长度的特殊情况
查找倒数第n个时，如果要求删除链表元素时，不要忘记记录应被删除元素的前一个元素
对于链表的题目，常常都会用到Two Pointers的思想。链表注意构建dummy头结点。在Java中，由于没有free函数，所以在删除一个节点的时候，无法用node = null来删除一个节点，需要用前一个节点来指向删除节点的下一个prev.next = node.next这样来删除node节点。

## 4.动态规划

这篇文章的主题是动态规划， 主要介绍LeetCode中一维动态规划的题目， 列表如下：
Climbing Stairs

Decode Ways

Unique Binary Search Trees

Maximum Subarray

Maximum Product Subarray

Best Time to Buy and Sell Stock

在介绍上述具体题目之前, 我们先说说动态规划的通常思路。 动态规划是一种算法思路（注意这里不要和递归混淆， 事实上递归和迭代只是两种不同的实现方法， 并不是算法）， 用一句话来总结就是， 动态规划是利用存储历史信息使得未来需要历史信息时不需要重新计 算， 从而达到降低时间复杂度， 用空间复杂度换取时间复杂度目的的方法。 我个人喜欢把动态规划分为以下几步：
1） 确定递推量。 这一步需要确定递推过程中要保留的历史信息数量和具体含义， 同时也会定下动态规划的维度；
2） 推导递推式。 根据确定的递推量， 得到如何利用存储的历史信息在有效时间（通常是常量或者线性时间）内得到当前的信息结果；
3） 计算初始条件。 有了递推式之后， 我们只需要计算初始条件， 就可以根据递推式得到我们想要的结果了。 通常初始条件都是比较简单的情况， 一般来说直接赋值即可；
4） （可选）考虑存储历史信息的空间维度。 这一步是基于对算法优化的考虑， 一般来说几维动态规划我们就用几维的存储空间是肯定可以实现的。 但是有时我们对于历史信息的要求不高， 比如这一步只需要用到上一步的历史信息， 而不需要更早的了， 那么我们可以只存储每一步的历史信息， 每步覆盖上一步的信息， 这样便可以少一维的存储空间， 从而优化算法的空间复杂度。
动态规划的时间复杂度是O(（维度）×（每步获取当前值所用的时间复杂度））。 基本上按照上面的思路， 动态规划的题目都可以解决， 不过最难的一般是在确定递推量， 一个好的递推量可以使得动态规划的时间复杂度尽量低。

接下来我们来看看具体题目， 一维动态规划的题目主要分成两类：

（1） 第一种是比较简单的， 直接地按照上面步骤就可以解出来的， 确定递归量， 然后按递归式迭代就可以得到。 这种类型的题目是： Climbing Stairs，Decode Ways和Unique Binary Search Trees。
Climbing Stairs中递推量很清晰， 就是爬到i级楼梯有多少种可行爬法。 而对于递推式我们可以看出， 要到达i级楼梯， 必须通过i－1级或者i－2级（以为只能爬一级或者两级）， 如此可以得到到达i级楼梯的方式有f（i）=f(i-1)+f(i-2)种， 这样递推式也就出来了。 而初始条件则是一级楼梯是一种解法， 两级楼梯是两种解法（2或者11）。 有了这些接下来递推到n级楼梯返回即可， 空间复杂度是O（n)(一维动态规划乘以每一步的常量操作）。 空间上我们发现每一步，只需要前两步的历史信息， 所以我们不需要存储所有历史信息， 只需要保存前两步， 然后迭代替换就可以了， 所以空间复杂度是O（2）=O（1）， 这里对应于上面的第四步。
Decode Ways中递推量也是类似的到达第i个字符可解析方式的数量， 递推式比Climbing Stairs稍微复杂一些， 要分情况讨论， 主要对于自己和前面一位组成数字的不同要分别处理一下， 这里就不列出来的，大家可以看看Decode Ways -- LeetCode。 虽然是分情况，不过每种情况也是可以常量时间更新信息的， 初始条件依然是非常简单的case， 空间上也是只需要保存前两步的信息， 所以时间和空间复杂度跟Climbing Stairs都是一样的。
Unique Binary Search Trees思路还是类似的， 递推式是稍有不同， 按左右子树划分然后进行累加， 最后归结为卡特兰数的模型。 这个问题仍然是一维动态规划， 但是求解单步信息时是一个线性操作， 所以时间复杂度是O（n^2)。 而空间上因为每一步都需要前面每一步的所有信息， 所以也无法优化， 是O(n)。

（2） 接下来我们介绍第二种类型， 虽然也是一维动态规划， 但是区别在于这类题目需要维护两个递推量， 所以思路上有一点技巧。 不过还是比较有通法的， 我通常把这种方法称为”局部最优和全局最优解法“。 这种方法中我们通常维护两个量， 一个是到目前为止最好的结果信息（全局最优）， 另一个必须包含新加进来的元素的最好的结果信息（局部最优）， 然后还是推导递推式， 计算初始条件， 跟动态规划的通常思路一样了。 Maximum Subarray和Best Time to Buy and Sell Stock就是这种类型的题目。
Maximum Subarray中对于递推量我们维护两个，一个是到目前为止最好的子数组， 而另一个量则是加入当前元素之后， 包含当前元素的最好的子数组， 最终我们是看全局最优的变量的最优值， 而局部最优却是我们在递推过程中维护全局最优所需要的。 递推式还是有点技巧， 第i+1步表达式如下：
  local[i+1]=Math.max(A[i], local[i]+A[i])，就是局部最优是一定要包含当前元素，所以不然就是上一步的局部最优local[i]+当前元素A[i]（因为local[i]一定包含第i个元素，所以不违反条件），但是如果local[i]是负的，那么加上他就不如不需要的，所以不然就是直接用A[i]；
  global[i+1]=Math(local[i+1],global[i])，有了当前一步的局部最优，那么全局最优就是当前的局部最优或者还是原来的全局最优（所有情况都会被涵盖进来，因为最优的解如果不包含当前元素，那么前面会被维护在全局最优里面，如果包含当前元素，那么就是这个局部最优）。
初始条件都是0或者第一个元素既可以了， 一遍扫过来， 每次迭代更新两个量（都是常量时间）， 所以时间是O(n)。 空间上可以看出只需要上一步的信息， 所以只需要保存上一步的全局最优和局部最优即可， 复杂度是O(2) = O(1)。

Maximum Product Subarray的题目模型跟Maximum Subarray比较类似，只是把加法改成了乘法，思路还是用这个方法，只是注意这里两个负数相乘可能得到更优的乘法结果，所以我们在维护局部最优时把局部的最小值也存下来，这样遇到负数时就可以得到也许更大的乘积。其他就跟Maximum Subarray是一致的了。

Best Time to Buy and Sell Stock跟Maximum Subarray是完全一样的， 也是维护两个量， 一个是到目前为止最好的交易（全局最优）， 另一个是在当前一天卖出的最佳交易（局部最优）， 其他步骤也是一样的， 这里就不列出来了。

可以看出， 上面五道一维动态规划的题目都是按照我前面列出的四个步骤进行求解的， 事实上所有动态规划题目都是按照这个基本思路来的。 掌握了套路之后就是看对具体问题要维护的递推量的选择了，这个个人感觉还是比较靠经验的， 熟能生巧。

## 5.回溯法

回溯法思想

#### 5.1 一般教科书概念上的讲解

   回溯算法实际上一个类似枚举的搜索尝试过程，主要是在搜索尝试过程中寻找问题的解，当发现已不满足求解条件时，就“回溯”返回，尝试别的路径。

   回溯法是一种选优搜索法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。

   许多复杂的，规模较大的问题都可以使用回溯法，有“通用解题方法”的美称。

#### 5.2 基本思想
   在包含问题的所有解的解空间树中，按照深度优先搜索的策略，从根结点出发深度探索解空间树。当探索到某一结点时，要先判断该结点是否包含问题的解，如果包含，就从该结点出发继续探索下去，如果该结点不包含问题的解，则逐层向其祖先结点回溯。（其实回溯法就是对隐式图的深度优先搜索算法）。

   若用回溯法求问题的所有解时，要回溯到根，且根结点的所有可行的子树都要已被搜索遍才结束。

   而若使用回溯法求任一个解时，只要搜索到问题的一个解就可以结束。

#### 5.3 解题一般步骤

  （1）针对所给问题，确定问题的解空间：首先应明确定义问题的解空间，问题的解空间应至少包含问题的一个（最优）解。

  （2）确定结点的扩展搜索规则

  （3）以深度优先方式搜索解空间，并在搜索过程中用剪枝函数避免无效搜索。

## 6.数学

数值计算在工业界是非常实用而且常见的具体问题, 所以在面试中出现频率非常高, 几乎可以说是必考题目。 LeetCode中关于数值运算的有以下题目：
Palindrome Number
Reverse Integer
Sqrt(x)
Pow(x, n)
Divide Two Integers
Max Points on a Line

在LeetCode中， 关于数值运算的题目分为三种类型， 下面将进行一一讲解。

第一种类型是最简单的， 就是对整数进行直接操作， 一般来说就是逐位操作， 比如反转， 比较等。
LeetCode中这类题目有Palindrome Number和Reverse Integer。 这类题目通常思路很清晰， 要注意的点就是对于边界情况的考虑， 对于数值而言，
主要问题是对于越界情况的考虑。 实际上越界问题是贯穿于所有数值计算题目的常见问题， 下面大多问题都会强调这点。
在Palindrome Number中因为只是进行判断， 并不需要修改数字， 所以没有越界问题。
思路比较简单， 就是每次取出最高位和最低位进行比较， 直到相遇或者出现违背条件（也就是不相等）即可返回。
对于Reverse Integer因为需要对数字进行反转， 所以需要注意反转后的数字可能会越界。 对于越界一般都是两种处理方法，
一种是返回最大（或者最小）数字， 一种则是抛出异常， 这个可以跟面试官讨论， 一般来说， 面试只要简单的返回最大最小或者dummy数字就可以了，
 但是处理和检查这种corner case（也就是越界）的想法一定要有和跟面试官讨论。

第二种题型是算术运算的题目， 比如乘除法， 阶乘， 开方等， LeetCode中这类题目有Sqrt(x)， Pow(x, n)和Divide Two Integers。
这种题目有时候看似复杂， 其实还是有几个比较通用的解法的， 下面主要介绍三种方法：
（1）二分法。 二分法是数值计算中很常用和易懂的方法。 基本思路是对于所求运算进行对半切割， 有时是排除一半， 有时则是得到可重复使用的历史数据。Sqrt(x)就是属于每次排除一半的类型， 对于要求的开方数字进行猜测， 如果大于目标， 则切去大的一半， 否则切去大的一半， 原理跟二分查找是一样的。Pow(x, n)则是属于重复利用数据的类型， 因为x的n次方实际上是两个x的n/2次方相乘， 所以我们只需要递归一次求出当前x的当前指数的1/2次方， 然后两个相乘就可以最后结果。 二分法很明显都是每次解决一半， 所以时间复杂度通常是O（logn）量级的。

（2）牛顿法。 这种方法可以说主要是数学方法， 不了解原理的朋友可以先看看牛顿法-维基百科。 Sqrt(x)就非常适合用牛顿法来解决， 因为它的递推式中的项都比较简单。 Pow(x, n)当然原理上也可以用牛顿法解答， 但是因为他的递推式中有项是进行x的开n次方的， 这个计算代价也是相当大的， 如果为了求x的n次方而去每一步求开n次方就没有太大实际意义了， 所以对于这种题我们一般不用牛顿法。

（3）位移法。 这种方法主要基于任何一个整数可以表示成以2的幂为底的一组基的线性组合， 对一个整数进行位数次迭代求解， 因为复杂度是位数的数量， 所以也跟二分法一样是O（logn）量级的。 Pow(x, n)和Divide Two Integers就是比较典型可以用这种方法解决的题目。 对于Pow(x, n)可以把n分解成位， 每次左移恰好是当前数的平方， 所以进行位数次迭代后即可以得到结果， 代码中很大的篇幅都是在处理越界问题， 而关于逐位迭代代码却很简短。 Divide Two Integers同样把结果分解成位， 每次对除数进行位移并且减去对应的除数来确定每一位上的结果。 这种方法可能理解起来没有二分法那么直观， 还是要消化一下哈。

第三种题目是解析几何的题目， 一般来说解析几何题目的模型都比较复杂， 而且实现细节比较多， 在面试中并不常见， LeetCode中也只有Max Points on a Line是属于这种题型。 这种题目没有什么通法， 主要就是要理清数学和几何模型， 比如Max Points on a Line中主要是理解判断点在直线的判断公式， 然后进行迭代实现。 实现细节还是比较多的， 需要对一些边界情况仔细考虑。

这篇总结主要列举了LeetCode中关于数值计算的题目， 介绍了这类问题的主要考点（比如越界判断）和常用的几种实用方法， 总体感觉这类问题是在面试中比较难很快写对的题目， 因为有一些边界情况和数值实现的细节。 因为出现频率很高， 还是需要对这类题目重点练习哈。

### 6.1 高精度
我们常见的一些基本的数据结构比如整型int或者浮点型float由于位数过多无法用内置类型存储，这时候我们就需要自己实现高精度的数据类型来进行存储和运算。这种问题在实际产品中还是比较实用的，所以相对来说也是面试中的常客。LeetCode中关于高精度的题目有以下几道：

Add Binary
Add Two Numbers
Plus One
Multiply Strings

Add Binary和Add Two Numbers是同一类型的题目，都是高精度中的加法运算，只是一个是二进制的，一个是十进制的，其实进制是无所谓的，代码基本可以统一起来用一种思路来实现。思路也很简单，就是从低位开始相加，一直维护进位就可以了。属于考察非常基本的数组操作，没有什么算法难度，主要看看coding实现能力。

Plus One也是一道常见的题目，他其实就是实现C++中++的运算符，因为只需要+1，所以其实比上面的题目更加简单。这道题的小陷阱就是它是用数组从高位到低位进行存储的，所以如果出现进位，那么需要重新分配空间，并给最高位赋1，其他位赋0即可。这里恰好引入一个点，就是高精度存储应该低位到高位存储还是反过来好，这也是面试中可能问到的问题。

Multiply Strings这道题是高精度的乘法运算，属于比较复杂的，需要对每一位的结果分别计算累加，其中的细节有点多，这里就不细说了，个人认为实现有点复杂，并不是很适合在面试中出现。

虽然说题目不多，但是这类题目的出现率却是非常高的，主要原因倒不是这种题目本身有很多的考点，而是它们特别好扩展，基本上来说问到这种题目，首先是考察一下coding能力，一般来说都是这种加减乘除的运算，接下来一定会是关于数据结构（或者说面向对象）的设计。这些题目的本身都是为高精度BigInteger服务的，面试官会问一些关于这个数据结构设计的问题，比如说如果让你来设计这个类，用什么数据结构来存（比如数组还是链表，各有什么利弊），需要哪些接口（构造函数，加减乘除运算等等），还有比如要设计构造函数，需要什么接口的构造函数（这里赋值构造函数，赋值运算符这些肯定是需要的，但是要注意必须提供对于常规类型比如int，long这些的接口，一个好的高精度类肯定是要对比它更弱的数据结构进行兼容的）。
上面我列举了一些可能在面试中会被继续考查的问题，也是一部分联想，像这种设计问题可以问得还是比较多的，也是非常常见的，大家可以自己多进行这种问题的准备和联想哈。

## 7.位运算

```
x ^ 0s = x      x & 0s = 0      x | 0s = x

x ^ 1s = ~x     x & 1s = x      x | 1s = 1s

x ^ x = 0       x & x = x       x | x = x
```

* n&(n-1) 去除 n 的位级表示中最低的那一位。
* n&(-n) 得到 n 的位级表示中最低的那一位。

不用额外变量交换两个整数:

```
a = a ^ b;
b = a ^ b;
a = a ^ b;
```


位运算一直编程和面试中的一个必须准备的主题。 不过现在面试中关于位运算的出现得不多，主要原因还是位运算太考察技巧了，很多时候很难在短时间内想出来，所以作为面试的题目显得有点太花时间了。LeetCode中关于位运算的题目有以下几道：
Single Number
Single Number II
Divide Two Integers
Pow(x, n)

先来说说Single Number， 这应该LeetCode中唯一一道纯粹用位运算解决的题目。
题目本身要求是找出唯一一个在数组中出现一次的整数，而其他都会出现两次。
这里利用到了位运算中异或的性质，就是两个相同的数进行异或会得到0，并且任何一个数与0的异或还是原数。
利用上面的性质，只要把数组中的元素一一异或起来，因为出现两次的会互相抵消，最后会只剩下那个出现一次的整数，所以就搞定了。

对于Single Number II，上面的方法就没办法了，因为出现三次就不能利用异或的性质了，所以这个题目得使用另外的方法了。
算法是对每个位出现1的次数进行统计，因为其他元素都会出现三次，所以最终这些位上的1的个数会是3的倍数。
如果我们把统计结果的每一位进行取余3，剩下的结果就会剩下那个出现一次的元素。这个方法对于出现k次都是通用的，包括上面的Single Number也可以用这种方法，
不过没有纯位运算的方法高大上哈。

接下来看看位运算在Divide Two Integers和Pow(x, n)这道题中的应用，主要是利用任何一个整数可以表示成以2的幂为底的一组基的线性组合的性质，
即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。对于Divide Two Integers基于上面的性质以及左移一位相当于乘以2，
我们先让除数左移直到大于被除数之前得到一个最大的基。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k，然后基继续右移迭代，直到基为0为止。
因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度可以达到O(logn)，也就是位的数量。而Pow(x, n)也是使用同样的方法，
把幂数n分解成2的幂数的基，然后进行依次平方然后求和。这个方法算是数值运算中的一个比较实用的方法，还是要熟练掌握。

这篇总结介绍了LeetCode中几个关于位运算的题目，虽然数量很少，不过思想上还是都挺有意义的，
如果面试中遇到能提出位运算的解法还是能加分不少，所以位运算在有些题目中还是一把关键的武器。