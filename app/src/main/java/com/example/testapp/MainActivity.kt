package com.example.testapp

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var dy: Int = 0
    private var layoutMngr: LinearLayoutManager? = null
    private var flag1: Boolean = false
    private var firstVisibleItem:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
           Toast.makeText(baseContext,edit_number.rawText,Toast.LENGTH_LONG).show()
        }


        layoutMngr = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
        recycler_view.apply {
            layoutManager = layoutMngr
            firstVisibleItem = layoutMngr!!.findFirstVisibleItemPosition()
            adapter = RvAdapter(getBooksList())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            flag1 = false


                        }
                        RecyclerView.SCROLL_STATE_DRAGGING -> {
                            flag1 = true

                        }
                        RecyclerView.SCROLL_STATE_SETTLING -> {
                            flag1 = false
                        }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    var currentVisibleItem = layoutMngr!!.findFirstVisibleItemPosition()

                    if(currentVisibleItem > firstVisibleItem){
                        val animSlideDown =
                            AnimationUtils.loadAnimation(applicationContext, R.anim.slideup)
                        animSlideDown.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationRepeat(p0: Animation?) {

                            }

                            override fun onAnimationEnd(p0: Animation?) {
                                lay1.visibility = View.GONE

                            }

                            override fun onAnimationStart(p0: Animation?) {

                            }

                        })
                        lay1.startAnimation(animSlideDown)
                        Log.i("RecyclerView scrolled: ", "scroll up!");

                    }

                    else{
                        val animSlideDown =
                            AnimationUtils.loadAnimation(applicationContext, R.anim.slidedown)
                        animSlideDown.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationRepeat(p0: Animation?) {
                            }

                            override fun onAnimationEnd(p0: Animation?) {
                                lay1.visibility = View.VISIBLE

                            }

                            override fun onAnimationStart(p0: Animation?) {

                            }

                            

                        })
                        lay1.startAnimation(animSlideDown)
                        Log.i("RecyclerView scrolled: ", "scroll down!");
                    }


                    firstVisibleItem = currentVisibleItem;

//                    if (dy > 0 && lay1.visibility == View.VISIBLE && flag1 ) {
//                        val animSlideDown =
//                            AnimationUtils.loadAnimation(applicationContext, R.anim.slideup)
//                        animSlideDown.setAnimationListener(object : Animation.AnimationListener {
//                            override fun onAnimationRepeat(p0: Animation?) {
//
//                            }
//
//                            override fun onAnimationEnd(p0: Animation?) {
//                                lay1.visibility = View.GONE
//
//                            }
//
//                            override fun onAnimationStart(p0: Animation?) {
//
//                            }
//
//                        })
//                        lay1.startAnimation(animSlideDown)
//
//                    } else if (dy < 0 && lay1.visibility != View.VISIBLE && flag1 ) {
//                        val animSlideDown =
//                            AnimationUtils.loadAnimation(applicationContext, R.anim.slidedown)
//                        animSlideDown.setAnimationListener(object : Animation.AnimationListener {
//                            override fun onAnimationRepeat(p0: Animation?) {
//                            }
//
//                            override fun onAnimationEnd(p0: Animation?) {
//                                lay1.visibility = View.VISIBLE
//
//                            }
//
//                            override fun onAnimationStart(p0: Animation?) {
//
//                            }
//
//                        })
//                        lay1.startAnimation(animSlideDown)
//
//                    }
                }
            })
        }
    }


    fun getBooksList(): List<Book> {
        val booksList: MutableList<Book> = ArrayList()
        booksList.add(Book("Kniga 1", "1234324"))
        booksList.add(Book("Kniga 2", "1234324"))
        booksList.add(Book("Kniga 3", "1234324"))
        booksList.add(Book("Kniga 4", "1234324"))
        booksList.add(Book("Kniga 5", "1234324"))
        booksList.add(Book("Kniga 6", "1234324"))
        booksList.add(Book("Kniga 7", "1234324"))
        booksList.add(Book("Kniga 8", "1234324"))
        booksList.add(Book("Kniga 9", "1234324"))
        booksList.add(Book("Kniga 10", "1234324"))
        booksList.add(Book("Kniga 1", "1234324"))
        booksList.add(Book("Kniga 1", "1234324"))
        booksList.add(Book("Kniga 1", "1234324"))
        booksList.add(Book("Kniga 1", "1234324"))
        return booksList
    }
}
