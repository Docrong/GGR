package com.ggr.leecode.hash;


/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class 两个链表转数字之和 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        addTwoNumbers(l1, l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = l1;
        //两链表各元素相加
        while (l2 != null) {
            l1.val = l1.val + l2.val;
            l2 = l2.next;
            if (l1.next == null) {
                l1.next = new ListNode(0);
            }
            l1 = l1.next;
        }
        //判断每个元素是否大于10
        l1 = result;
        while (l1.next != null) {
            if (l1.val > 9) {
                l1.next.val = l1.next.val + l1.val / 10;
                l1.val = l1.val % 10;
            }
            if (l1.next.val == 0 && l1.next.next == null) {
                l1.next = null;
            } else {
                l1 = l1.next;
            }
        }
        //判断最后一个元素是否大于10
        if (l1.val > 9) {
            l1.next = new ListNode(0);
            l1.next.val = l1.next.val + l1.val / 10;
            l1.val = l1.val % 10;
        }
        System.out.println(result.val);
        System.out.println(result.next.val);
        return result;
    }

}

