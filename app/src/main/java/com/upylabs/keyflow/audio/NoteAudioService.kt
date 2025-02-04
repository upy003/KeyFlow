package com.upylabs.keyflow.audio

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.upylabs.keyflow.data.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteAudioService(private val context: Context) {
    private val player = ExoPlayer.Builder(context).build()
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    fun playNote(note: Note) {
        // Format der Audiodateien: piano_[note][octave].wav
        // z.B. piano_c4.wav, piano_d4.wav, etc.
        val fileName = "piano_${note.name.lowercase()}${note.octave}.wav"
        
        try {
            val afd = context.assets.openFd(fileName)
            player.setMediaItem(MediaItem.fromUri("asset:///$fileName"))
            player.prepare()
            player.play()
            
            _isPlaying.value = true
            player.addListener(object : androidx.media3.common.Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    if (state == androidx.media3.common.Player.STATE_ENDED) {
                        _isPlaying.value = false
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun release() {
        player.release()
    }
} 