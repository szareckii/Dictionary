package com.szareckii.historyscreen.view.history

import android.os.Bundle
import android.view.MenuItem
import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.historyscreen.view.history.adapter.HistoryAdapter
import androidx.lifecycle.Observer
import com.szareckii.core.BaseActivity
import com.szareckii.historyscreen.R
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.viewmodel.ext.android.viewModel


class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override val model: HistoryViewModel by viewModel()

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setActionbarHomeButtonAsUp()
        iniViewModel()
        initViews()
    }

    // Сразу запрашиваем данные из локального репозитория
    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    // Вызовется из базовой Activity, когда данные будут готовы
    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    // Переопределяем нажатие на стрелку Назад, чтобы возвращаться по нему
    // на главный экран
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Устанавливаем кнопку Назад в ActionBar
    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun iniViewModel() {
        if (history_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it, true) })
    }

    // Инициализируем адаптер и передаем его в RecyclerView
    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }

    override fun showWordLocalRep(data: List<DataModel>) {
        TODO("Not yet implemented")
    }
}

