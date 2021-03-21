package com.szareckii.historyscreen.view.history

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.szareckii.core.BaseActivity
import com.szareckii.historyscreen.R
import com.szareckii.historyscreen.injectDependencies
import com.szareckii.model.data.AppState
import com.szareckii.model.data.dto.DataModelDto
import com.szareckii.model.data.userdata.DataModel
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.scope.currentScope

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override lateinit var model: HistoryViewModel

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }
    override val layoutRes = R.layout.activity_history

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionbarHomeButtonAsUp()
        iniViewModel()
        initViews()
    }

    private fun hideUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }


    // Сразу запрашиваем данные из локального репозитория
    override fun onResume() {
        super.onResume()
        model.getData("", false)
        hideUI()
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
        check(history_activity_recyclerview.adapter == null) { getString(R.string.viewmodel_is_null_hist) }

        injectDependencies()
        val viewModel: HistoryViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    // Инициализируем адаптер и передаем его в RecyclerView
    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }

    override fun showWordLocalRep(data: List<DataModel>) {
        TODO("Not yet implemented")
    }
}

